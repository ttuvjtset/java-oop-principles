package kt12_producer_consumer;


public class Main {
    //  private static BlockingQueue<Order> queue = new ArrayBlockingQueue<Order>(2000);

    public static void main(String[] args) throws InterruptedException {
        Orders orders = new Orders();

        OrdersCreator ordersCreator = new OrdersCreator(orders);
        Thread producerThread = new Thread(ordersCreator);
        producerThread.start();


        startOrdersProcessorThread(orders, "Cons 1");
        startOrdersProcessorThread(orders, "Cons 2");
        startOrdersProcessorThread(orders, "Cons 3");
        startOrdersProcessorThread(orders, "Cons 4");


    }

    private static void startOrdersProcessorThread(Orders orders, String threadName) {
        OrderProcessor orderProcessor = new OrderProcessor(orders, threadName);
        Thread consumerThread = new Thread(orderProcessor);
        consumerThread.start();
    }
}
