package kt13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {

    public static void main(String[] args) throws Exception {
        AtomicInteger uniqueTicketIDs = new AtomicInteger(1);
        TicketStorage storage = new TicketStorage();

        ExecutorService executor = Executors.newFixedThreadPool(5);

        Future<Integer> future1 = executor.submit(new TicketOffice(uniqueTicketIDs, storage, "TicketOffice1", 2));
        Future<Integer> future2 = executor.submit(new TicketOffice(uniqueTicketIDs, storage, "TicketOffice2", 3));

        executor.submit(new Carousel(storage, "Carousel1"));
        executor.submit(new Carousel(storage, "Carousel2"));
        executor.submit(new Carousel(storage, "Carousel3"));

        System.out.println("The created ticket sum for ticket office 1: " + future1.get());
        System.out.println("The created ticket sum for ticket office 2: " + future2.get());

        System.out.println("All tickets sum: " + (future1.get() + future2.get()));

        executor.shutdown();
    }
}
