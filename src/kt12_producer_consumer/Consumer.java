package kt12_producer_consumer;


import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Order> queue;
    private String threadName;

    public Consumer(BlockingQueue<Order> queue, String threadName) {
        this.queue = queue;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                Order order = queue.take();
                System.out.println(threadName + "  " + order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
