package _martin.prax13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Controller {

    public static void main(String[] args) throws Exception {

        TicketStorage storage = new TicketStorage();

        ExecutorService executor = Executors.newFixedThreadPool(5);

        Future<Integer> future1 = executor.submit(new Cashier(storage, "Cashier1", 2));
        Future<Integer> future2 = executor.submit(new Cashier(storage, "Cashier2", 3));

        executor.submit(new Carousel(storage, "Carousel1"));
        executor.submit(new Carousel(storage, "Carousel2"));
        executor.submit(new Carousel(storage, "Carousel3"));

        System.out.println("The created ticket sum for thread 1 " + future1.get());
        System.out.println("The created ticket sum for thread 2 " + future2.get());

        System.out.println("All tickets sum " + (future1.get() + future2.get()));

        executor.shutdown();
    }
}
