package kt12_producer_consumer;


public class OrdersCreator implements Runnable {
    private static final int SLEEP_DURATION = 2;
    private static final int BUSINESS_CLIENTS_WITH_BIG_ORDERS = 250;
    private static final int BUSINESS_CLIENTS_WITH_SHORT_ORDERS = 450;

    //private BlockingQueue<Order> queue;

    private Orders orders;
    private int clientNumber = 1;

    OrdersCreator(Orders orders) {
        this.orders = orders;
    }

    @Override
    public void run() {
/*        try {
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
            createBusinessBigOrders();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    private void createBusinessBigOrders() throws InterruptedException {
        for (int i = 0; i < BUSINESS_CLIENTS_WITH_BIG_ORDERS; i++) {
            orders.addOrder(new OrderBusinessClient("Name", "Address", getClientNumberAndIterate(),
                    7, 1, 222));
            try {
                Thread.sleep(SLEEP_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createBusinessShortOrders() throws InterruptedException {
        for (int i = 0; i < BUSINESS_CLIENTS_WITH_SHORT_ORDERS; i++) {
            orders.addOrder(new OrderBusinessClient("Name", "Address", getClientNumberAndIterate(),
                    5, 1, 222));
            try {
                Thread.sleep(SLEEP_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createPrivateOrders() throws InterruptedException {
        for (int i = 0; i < 700; i++) {
            orders.addOrder(new OrderPrivateClient("Name", "Address", getClientNumberAndIterate(),
                    4, 1));
            try {
                Thread.sleep(SLEEP_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int getClientNumberAndIterate() {
        return clientNumber++;
    }
}
