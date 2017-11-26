package kt12_producer_consumer;

import java.util.ArrayList;
import java.util.List;


class Orders {
    private List<Order> orders = new ArrayList<>();

    synchronized void addOrder(Order order) throws InterruptedException {
        orders.add(order);
        notifyAll();
    }

    synchronized Order getOrder() throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        return orders.remove(0);
    }
}
