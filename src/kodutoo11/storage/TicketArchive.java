package kodutoo11.storage;

import kodutoo11.airline.BoardingPass;

import java.util.ArrayList;
import java.util.List;


public class TicketArchive {
    private List<BoardingPass> archivedPasses = new ArrayList<>();

    public synchronized List<BoardingPass> getArchivedPasses() {
        return archivedPasses;
    }

    public synchronized void addTicket(BoardingPass ticket) {
        archivedPasses.add(ticket);
    }
}
