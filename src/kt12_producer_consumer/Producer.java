package kt12_producer_consumer;


public class Producer implements Runnable {
    //private BlockingQueue<Order> queue;
    private Orders orders;
    private int clientNumber = 1;

    Producer(Orders orders) {
        this.orders = orders;
    }

    @Override
    public void run() {
        try {
            createPrivateOrders();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            createBusinessShortOrders();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            createBusinessLongOrders();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createBusinessLongOrders() throws InterruptedException {
        for (int i = 0; i < 250; i++) {
            orders.addOrder(new OrderBusinessClient("Name", "Address", getClientNumberAndIterate(), 7, 1, 222));
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createBusinessShortOrders() throws InterruptedException {
        for (int i = 0; i < 450; i++) {
            orders.addOrder(new OrderBusinessClient("Name", "Address", getClientNumberAndIterate(), 5, 1, 222));
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createPrivateOrders() throws InterruptedException {
        for (int i = 0; i < 700; i++) {
            orders.addOrder(new OrderPrivateClient("Name", "Address", getClientNumberAndIterate(), 4, 1));
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
