package kt11_threads.storage;

import kt11_threads.airline.BoardingPass;

import java.util.ArrayList;
import java.util.List;


public class TicketsCollectorForArchive implements Runnable {

    private List<BoardingPass> ticketsCollectedForArchiving;
    private List<BoardingPass> bufferedArchive;
    private TicketOfflineArchive ticketOfflineArchive;

    public TicketsCollectorForArchive(TicketOfflineArchive ticketOfflineArchive) {
        this.ticketsCollectedForArchiving = new ArrayList<>();
        this.bufferedArchive = new ArrayList<>();
        this.ticketOfflineArchive = ticketOfflineArchive;
    }

    public synchronized List<BoardingPass> getTicketsCollectedForArchiving() {
        return ticketsCollectedForArchiving;
    }

    public synchronized void addTicket(BoardingPass ticket) {
        ticketsCollectedForArchiving.add(ticket);
        bufferedArchive.add(ticket);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }

            System.out.println("Archive buffer: " + bufferedArchive.size());
            ticketOfflineArchive.addToArchivedTickets(bufferedArchive);
            bufferedArchive.clear();
            System.out.println("Archived: " + ticketOfflineArchive.getArchivedTickets().size());
        }
    }
}
