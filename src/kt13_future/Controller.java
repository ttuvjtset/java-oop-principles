package kt13_future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {
    private static final int PAUSE = 1000;

    private volatile static boolean stop = false;

    public static void main(String[] args) throws Exception {
        AtomicInteger uniqueTicketIDs = new AtomicInteger(1);
        TotalWeight totalWeight = new TotalWeight();
        TicketStorage storage = new TicketStorage();
        Flag flag = new Flag();


        ExecutorService executor = Executors.newFixedThreadPool(5);

        Future<Integer> ticketOffice1 = executor.submit(new TicketOffice(uniqueTicketIDs, storage, "TicketOffice1", 2));
        Future<Integer> ticketOffice2 = executor.submit(new TicketOffice(uniqueTicketIDs, storage, "TicketOffice2", 3));

        Future<Double> carousel1 = executor.submit(new Carousel(storage, "Carousel1", totalWeight, flag, stop));
        Future<Double> carousel2 = executor.submit(new Carousel(storage, "Carousel2", totalWeight, flag, stop));
        Future<Double> carousel3 = executor.submit(new Carousel(storage, "Carousel3", totalWeight, flag, stop));


        Thread.sleep(PAUSE);
        //flag.setRedFlag();
        // stop = true;

        System.out.println("The created ticket sum for ticket office 1: " + ticketOffice1.get());
        System.out.println("The created ticket sum for ticket office 2: " + ticketOffice2.get());

        System.out.println("Average weight of carousel 1: " + carousel1.get());
        System.out.println("Average weight of carousel 2: " + carousel2.get());
        System.out.println("Average weight of carousel 3: " + carousel3.get());

        System.out.println("All tickets sum: " + (ticketOffice1.get() + ticketOffice2.get()));

        executor.shutdown();
    }
}
