package task10_threads;

import task10_threads.airline.BoardingPass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketStorage {
    private ArrayList<BoardingPass> tickets = new ArrayList<>();

    private List<BoardingPass> passes = new ArrayList<>();

    public synchronized void addTicket(BoardingPass boardingPass) {
        tickets.add(boardingPass);
    }
    // kaks objkekti ei saa seda objekti samaaegselt kasutada!


    public synchronized Optional<BoardingPass> getTicket() {
        if (!tickets.isEmpty()) {
            return Optional.of(tickets.remove(0));

        }
        return Optional.empty(); // kui ei ole, siis tuleb tuhi optional
    }
}
