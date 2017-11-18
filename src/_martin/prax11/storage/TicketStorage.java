package _martin.prax11.storage;

import _martin.prax11.airline.BoardingPass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketStorage {
	
	List<BoardingPass> passes = new ArrayList<>();
	
	public synchronized void addTicket(BoardingPass ticket) {
		passes.add(ticket);
	}

	public synchronized Optional<BoardingPass> getTicket() {
		if (!passes.isEmpty()) {
			return Optional.of(passes.remove(0));
		}
		return Optional.empty();
	}
}






