package _martin.prax13;

import java.util.concurrent.Callable;

public class Cashier implements Callable<Integer> {

    private String name;
    private TicketStorage storage;
    private int soldTickets = 0;
    private int delay;

    public Cashier(TicketStorage storage, String name, int delay) {
        this.storage = storage;
        this.name = name;
        this.delay = delay;
    }

    @Override
    public Integer call() throws Exception {
        while (TicketStorage.runningCarousels != 0) {
            Thread.sleep(delay);
            Ticket ticket = new Ticket();
            storage.addTicket(ticket);
            soldTickets++;

            System.out.println(name + " created ticket number " + ticket.getTicketNumber());
        }
        return soldTickets;
    }
}
