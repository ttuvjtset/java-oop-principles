package task13;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        AtomicInteger uniqueIDs = new AtomicInteger(1);
        Tickets tickets = new Tickets();

        startTicketOffice(uniqueIDs, tickets, 2);
        startTicketOffice(uniqueIDs, tickets, 3);


        Carousel carousel = new Carousel(tickets);
        Thread threadCarousel = new Thread(carousel);
        threadCarousel.start();

    }

    private static void startTicketOffice(AtomicInteger uniqueIDs, Tickets tickets, int timeout) {
        TicketOffice ticketOffice1 = new TicketOffice(uniqueIDs, tickets, timeout);
        FutureTask<Integer> futureTask1 = new FutureTask<>(ticketOffice1);
        Thread thread1 = new Thread(futureTask1);
        thread1.start();
    }
}
