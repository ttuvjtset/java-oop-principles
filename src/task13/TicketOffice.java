package task13;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketOffice implements Callable<Integer> {
    private AtomicInteger uniqueTicketID;
    private Tickets tickets;
    private int sellTimeout;


    TicketOffice(AtomicInteger uniqueTicketID, Tickets tickets, int sellTimeout) {
        this.uniqueTicketID = uniqueTicketID;
        this.tickets = tickets;
        this.sellTimeout = sellTimeout;
    }

    @Override
    public Integer call() throws Exception {
        while (!Thread.interrupted()) {
            Ticket ticket = new Ticket(uniqueTicketID);
            Person person = new Person();
            ticket.assignToPerson(person);
            tickets.addTicket(ticket);
            Thread.sleep(sellTimeout);
            System.out.println("added");
        }

        return 0;
    }
}
