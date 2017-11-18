package task10_threads.airline;

import task10_threads.TicketStorage;

public class NordicaTicketService implements AirlineTicketService{

	private TicketStorage ticketStorage;
	
	public NordicaTicketService(TicketStorage ticketStorage) {

		this.ticketStorage = ticketStorage;
	}


	@Override
	public void run() {
		for (int i = 0; i < 30000; i++) {
			BoardingPass ticket = new Ticket("John", "Smith", i);
			ticketStorage.addTicket(ticket);
//			try {
//				//Thread.sleep(20);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
}
