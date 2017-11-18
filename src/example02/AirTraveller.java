package example02;

import example02.airline.AirlineTicketService;
import example02.airline.NordicaTicketService;
import example02.airport.AirportController;
import example02.securitygate.SecurityGate;
import example02.securitygate.SecurityGateDatabase;

public class AirTraveller {
	public static void main(String[] args) {
		AirlineTicketService ticketService = new NordicaTicketService();
		SecurityGateDatabase securityGate = new SecurityGate();
		
		AirportController airport = new AirportController(ticketService,
				securityGate);
		
		airport.processTickets();
	}
}
