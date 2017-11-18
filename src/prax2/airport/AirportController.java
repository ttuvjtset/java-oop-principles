package prax2.airport;

import _kruglovaa.prax2.airline.AirlineTicketService;
import _kruglovaa.prax2.securitygate.SecurityGateDatabase;

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
