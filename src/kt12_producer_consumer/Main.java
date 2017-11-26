package kt12_producer_consumer;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    private static BlockingQueue<Order> queue = new ArrayBlockingQueue<Order>(2000);

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer(queue);
        Thread producerThread = new Thread(producer);
        producerThread.start();



        startConsumerThread("Cons 1");
        startConsumerThread("Cons 2");
        startConsumerThread("Cons 3");
        startConsumerThread("Cons 4");


    }

    private static void startConsumerThread(String threadName) {
        Consumer consumer = new Consumer(queue, threadName);
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
    }
}
