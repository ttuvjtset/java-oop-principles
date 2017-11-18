package _martinn.prax02;

import _kruglovaa.prax2.airline.AirlineTicketService;
import _kruglovaa.prax2.airline.NordicaTicketService;
import _kruglovaa.prax2.airport.AirportController;
import _kruglovaa.prax2.securitygate.SecurityGate;
import _kruglovaa.prax2.securitygate.SecurityGateDatabase;

public class AirTraveller {
	public static void main(String[] args) {
		AirlineTicketService ticketService = new NordicaTicketService();
		SecurityGateDatabase securityGate = new SecurityGate();
		
		AirportController airport = new AirportController(ticketService,
				securityGate);
		
		airport.processTickets();
	}
}
