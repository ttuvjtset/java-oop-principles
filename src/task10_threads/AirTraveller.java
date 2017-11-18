package task10_threads;

import task10_threads.airport.AirportController;

public class AirTraveller {
	public static void main(String[] args) throws InterruptedException {
//		AirlineTicketService ticketService = new NordicaTicketService();
//		SecurityGateDatabase securityGate = new SecurityGate();
//
//		AirportController martin.prax.prax.airport = new AirportController(ticketService,
//				securityGate);
//
//		martin.prax.prax.airport.processTickets();

		TicketStorage storage = new TicketStorage();
		AirportController controller = new AirportController(storage);

		controller.runAirport();
	}
}
