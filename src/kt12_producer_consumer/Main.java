package kt12_producer_consumer;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int PROCESSOR_THREAD_AMOUNT = 4;
    private static final int TOTAL_THREAD_AMOUNT = 5;
    private static final int PROCESSING_TIMEOUT = 7;

    //  private static BlockingQueue<Order> queue = new ArrayBlockingQueue<Order>(2000);

    public static void main(String[] args) throws InterruptedException {
        Orders orders = new Orders();

        ExecutorService executorService = Executors.newFixedThreadPool(TOTAL_THREAD_AMOUNT);
        Runnable runnableOrdersCreator = new OrdersCreator(orders);
        executorService.execute(runnableOrdersCreator);

        for (int i = 1; i <= PROCESSOR_THREAD_AMOUNT; i++) {
            Runnable runnableOrderProcessor = new OrderProcessor(orders, "Processor " + i);
            executorService.execute(runnableOrderProcessor);
        }

        executorService.awaitTermination(PROCESSING_TIMEOUT, TimeUnit.SECONDS);
        executorService.shutdownNow();
    }
}
