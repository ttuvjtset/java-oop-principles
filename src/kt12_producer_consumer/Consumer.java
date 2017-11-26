package kt12_producer_consumer;


public class Consumer implements Runnable {
    //private BlockingQueue<Order> queue;
    private Orders orders;
    private String threadName;

    public Consumer(Orders orders, String threadName) {
        this.orders = orders;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                Order order = orders.getOrder();
                System.out.println(threadName + "  " + order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
