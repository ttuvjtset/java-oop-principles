package example02.airport;

import example02.airline.AirlineTicketService;
import example02.securitygate.SecurityGateDatabase;

public class AirportController {

	AirlineTicketService ticketService;
	SecurityGateDatabase securityGate;
	
	public AirportController(AirlineTicketService ticketService,
			SecurityGateDatabase securityGateDatabase) {
		this.ticketService = ticketService;
		this.securityGate = securityGateDatabase;
	}
	
	public void processTickets() {
		while (ticketService.hasNextBoardingPass()) {
			securityGate.registerTicket(ticketService.getNextBoardingPass());
		}
	}
}
