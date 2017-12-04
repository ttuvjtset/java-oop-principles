package kt13_future;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketStorage {

    static int runningCarousels = -1;
    private final List<Ticket> tickets = new ArrayList<>();

    public void addTicket(Ticket ticket) {
        synchronized (tickets) {
            tickets.add(ticket);
            tickets.notifyAll();
        }
    }

    Ticket popTicket() throws InterruptedException {
        synchronized (tickets) {
            Optional<Ticket> ticketInContainer = Optional.empty();
            while (!ticketInContainer.isPresent()) {
                ticketInContainer = tickets.stream().findAny();
                if (!ticketInContainer.isPresent()) {
                    tickets.wait();
                }
            }
            tickets.remove(ticketInContainer.get());
            return ticketInContainer.get();
        }
    }

    synchronized void increment() {
        System.out.println("incrementing");
        if (runningCarousels < 0) {
            runningCarousels = 1;
        } else {
            runningCarousels++;
        }
    }

    synchronized void decrement() {

        System.out.println("decrementing");
        runningCarousels--;
    }

}
