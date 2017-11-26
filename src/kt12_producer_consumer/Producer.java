package kt12_producer_consumer;


import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<Order> queue;
    private int clientNumber = 1;

    Producer(BlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        createPrivateOrders();
        createBusinessShortOrders();
        createBusinessLongOrders();
    }

    private void createBusinessLongOrders() {
        for (int i = 0; i < 250; i++) {
            queue.add(new OrderBusinessClient("Name", "Address", getClientNumberAndIterate(), 7, 1, 222));
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createBusinessShortOrders() {
        for (int i = 0; i < 450; i++) {
            queue.add(new OrderBusinessClient("Name", "Address", getClientNumberAndIterate(), 5, 1, 222));
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createPrivateOrders() {
        for (int i = 0; i < 700; i++) {
            queue.add(new OrderPrivateClient("Name", "Address", getClientNumberAndIterate(), 4, 1));
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int getClientNumberAndIterate() {
        return clientNumber++;
    }
}
