package task10_threads.securitygate;

import task10_threads.TicketStorage;
import task10_threads.airline.BoardingPass;

import java.util.Optional;

public class SecurityGate implements SecurityGateDatabase {
	private TicketStorage ticketStorage;

	public SecurityGate(TicketStorage ticketStorage) {
		this.ticketStorage = ticketStorage;
	}

	//	public SecurityGate(TicketStorage ticketStorage) {
//		this.ticketStorage = ticketStorage;
//	}

	private void registerTicket(BoardingPass boardingPass){
		System.out.println("Registreeritud pardakaart nr: "
				+ boardingPass.getTicketCode()
				+ ": "
				+ boardingPass.getPassengerFirstName()
				+ " "
				+ boardingPass.getPassengerLastName());
	}

	@Override
	public void run() {
		try {
			fetchTickets();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void fetchTickets() throws InterruptedException {
		while(!Thread.interrupted()) {
			Optional<BoardingPass> ticket = ticketStorage.getTicket();
			ticket.ifPresent(this::registerTicket);

/*			if (ticket.isPresent()) {
				registerTicket(ticket.get());
			}*/
		}
	}
}
