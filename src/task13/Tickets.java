package task13;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Tickets {
    private List<Ticket> tickets = new ArrayList<>();

    synchronized void addTicket(Ticket ticket) throws InterruptedException {
//        tickets.add(ticket);
//        notifyAll();

        synchronized (tickets) {
            tickets.add(ticket);
            tickets.notify();
        }
    }

    synchronized Ticket popTicket() throws InterruptedException {
//        while (tickets.isEmpty()) {
//            wait();
//        }
//        return tickets.remove(0);

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
}
