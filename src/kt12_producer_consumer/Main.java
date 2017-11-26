package kt12_producer_consumer;


public class Main {
 //  private static BlockingQueue<Order> queue = new ArrayBlockingQueue<Order>(2000);

    public static void main(String[] args) throws InterruptedException {
        Orders orders = new Orders();

        Producer producer = new Producer(orders);
        Thread producerThread = new Thread(producer);
        producerThread.start();



        startConsumerThread(orders,"Cons 1");
        startConsumerThread(orders,"Cons 2");
        startConsumerThread(orders,"Cons 3");
        startConsumerThread(orders,"Cons 4");


    }

    private static void startConsumerThread(Orders orders, String threadName) {
        Consumer consumer = new Consumer(orders, threadName);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
    }
}
