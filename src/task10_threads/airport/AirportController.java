package task10_threads.airport;

import task10_threads.TicketStorage;
import task10_threads.airline.AirlineTicketService;
import task10_threads.airline.NordicaTicketService;
import task10_threads.securitygate.SecurityGate;
import task10_threads.securitygate.SecurityGateDatabase;

public class AirportController {

	//AirlineTicketService ticketService;
	//SecurityGateDatabase securityGate;
	TicketStorage ticketStorage;
	
	public AirportController(TicketStorage ticketStorage) {
		this.ticketStorage = ticketStorage;
//		this.ticketService = ticketService;ticketStorage
//		this.securityGate = securityGateDatabase;


		//this.ticketService = new NordicaTicketService(ticketStorage);
		//this.securityGate = new SecurityGate(ticketStorage);


	}

	public void runAirport() throws InterruptedException {
		// create service runner
		AirlineTicketService ticketService = new NordicaTicketService(ticketStorage);
		Thread nordicaRunner = new Thread(ticketService);
		nordicaRunner.start();

		AirlineTicketService ticketService1 = new NordicaTicketService(ticketStorage);
		Thread nordicaRunner2 = new Thread(ticketService1);
		nordicaRunner2.start();

		SecurityGateDatabase gate1 = new SecurityGate(ticketStorage);
		SecurityGateDatabase gate2 = new SecurityGate(ticketStorage);

		Thread gateRunner1 = new Thread(gate1);
		Thread gateRunner2 = new Thread(gate2);
		gateRunner1.start();
		gateRunner2.start();

		Thread.sleep(7000);

		gateRunner1.interrupt();
		gateRunner2.interrupt();


	//	SecurityGateDatabase securityGateDatabase = new SecurityGate();

	}
//	public void processTickets() {
//		while (ticketService.hasNextBoardingPass()) {
//			securityGate.registerTicket(ticketService.getNextBoardingPass());
//		}
//	}
	
	
	
}
