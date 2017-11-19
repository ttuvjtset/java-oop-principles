package _martin.prax02;

import _martin.prax02.airline.AirlineTicketService;
import _martin.prax02.airline.NordicaTicketService;
import _martin.prax02.airport.AirportController;
import _martin.prax02.securitygate.SecurityGate;
import _martin.prax02.securitygate.SecurityGateDatabase;

public class AirTraveller {
	public static void main(String[] args) {
		AirlineTicketService ticketService = new NordicaTicketService();
		SecurityGateDatabase securityGate = new SecurityGate();
		
		AirportController airport = new AirportController(ticketService,
				securityGate);
		
		airport.processTickets();
	}
}
