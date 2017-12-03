package _martin.prax13;

import java.util.concurrent.Callable;

public class Carousel implements Callable<Integer> {

    private String name;
    private TicketStorage storage;
    private int rideCount = 0;

    public Carousel(TicketStorage storage, String name) {
        this.storage = storage;
        this.name = name;
        this.storage.increment();
    }

    @Override
    public Integer call() throws Exception {
        while (rideCount < 500) {
            Ticket ticket = storage.popTicket();
            rideCount++;

            System.out.println(name + " is ready for ticket number " + ticket.getTicketNumber());
            ;
        }
        storage.decrement();
        return rideCount;
    }

}
