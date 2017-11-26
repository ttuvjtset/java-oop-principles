package kt12_producer_consumer;

import java.util.ArrayList;
import java.util.List;


public class Orders {
    private List<Order> orders = new ArrayList<>();

    public synchronized void addOrder(Order order) throws InterruptedException {
        orders.add(order);
        notifyAll();
    }

    public synchronized Order getOrder(Order order) throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        return orders.remove(0);
    }
}
