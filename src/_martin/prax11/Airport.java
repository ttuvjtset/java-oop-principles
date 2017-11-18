package _martin.prax11;

import _martin.prax11.airport.AirportController;
import _martin.prax11.storage.TicketStorage;

public class Airport {
	public static void main(String[] args) {
		
		TicketStorage storage = new TicketStorage();
		AirportController controller = new AirportController(storage);
		
		try {
			controller.runAirport();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
