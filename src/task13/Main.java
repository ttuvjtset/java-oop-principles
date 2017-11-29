package task13;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AtomicInteger uniqueIDs = new AtomicInteger(1);
        Tickets tickets = new Tickets();
//
//        startTicketOffice(uniqueIDs, tickets, 2);
//        startTicketOffice(uniqueIDs, tickets, 3);


//        Carousel carousel = new Carousel(tickets);
//        FutureTask<Integer> futureTask2 = new FutureTask<>(carousel);
//        Thread futureTask3 = new Thread(futureTask2);
//        futureTask3.start();

        ExecutorService pool = Executors.newFixedThreadPool(5);
        Callable<Integer> cashier1 = new TicketOffice(uniqueIDs, tickets, 2);
        Callable<Integer> cashier2 = new TicketOffice(uniqueIDs, tickets, 3);

        Future<Integer> future1 = pool.submit(cashier1);
        Future<Integer> future2 = pool.submit(cashier2);

        pool.submit(new Carousel(tickets));
        pool.submit(new Carousel(tickets));
        pool.submit(new Carousel(tickets));


        System.out.println("created sum" + (future1.get() + future2.get()));

        pool.shutdown();


    }

    private static void startTicketOffice(AtomicInteger uniqueIDs, Tickets tickets, int timeout) {
        TicketOffice ticketOffice1 = new TicketOffice(uniqueIDs, tickets, timeout);
        FutureTask<Integer> futureTask1 = new FutureTask<>(ticketOffice1);
        Thread thread1 = new Thread(futureTask1);
        thread1.start();
    }
}
