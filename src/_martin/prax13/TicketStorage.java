package _martin.prax13;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketStorage {

    public static int runningCarousels = -1;
    private final List<Ticket> tickets = new ArrayList<>();

    public void addTicket(Ticket ticket) {
        synchronized (tickets) {
            tickets.add(ticket);
            tickets.notify();
        }
    }

    public Ticket popTicket() throws InterruptedException {
        synchronized (tickets) {
            Optional<Ticket> ticket = Optional.empty();
            while (!ticket.isPresent()) {
                ticket = tickets.stream().findAny();
                if (!ticket.isPresent()) {
                    tickets.wait();
                }
            }
            tickets.remove(ticket.get());
            return ticket.get();
        }
    }

    public synchronized void increment() {
        if (runningCarousels < 0) {
            runningCarousels = 1;
        } else {
            runningCarousels++;
        }
    }

    public synchronized void decrement() {
        runningCarousels--;
    }

}
