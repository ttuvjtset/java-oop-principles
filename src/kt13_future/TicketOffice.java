package kt13_future;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketOffice implements Callable<Integer> {

    private AtomicInteger uniqueTicketIDs;
    private TicketStorage ticketStorage;
    private String name;
    private int delay;
    private int soldTickets;

    TicketOffice(AtomicInteger uniqueTicketIDs, TicketStorage ticketStorage, String name, int delay) {
        this.uniqueTicketIDs = uniqueTicketIDs;
        this.ticketStorage = ticketStorage;
        this.name = name;
        this.delay = delay;
        this.soldTickets = 0;
    }

    @Override
    public Integer call() throws Exception {
        while (TicketStorage.runningCarousels != 0) {
            Thread.sleep(delay);

            Ticket ticket = new Ticket(uniqueTicketIDs);
            ticketStorage.addTicket(ticket);
            soldTickets++;

            System.out.println(name + " created ticket number " + ticket.getTicketNumber());
        }
        return soldTickets;
    }
}
