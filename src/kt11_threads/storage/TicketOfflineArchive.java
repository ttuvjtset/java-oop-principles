package kt11_threads.storage;

import kt11_threads.airline.BoardingPass;

import java.util.ArrayList;
import java.util.List;


public class TicketOfflineArchive {
    private List<BoardingPass> archivedTickets = new ArrayList<>();

    List<BoardingPass> getArchivedTickets() {
        return archivedTickets;
    }

    void addToArchivedTickets(List<BoardingPass> archivedTickets) {
        this.archivedTickets.addAll(archivedTickets);
    }
}
