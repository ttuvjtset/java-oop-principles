package _martin.prax02.airline;

import java.util.ArrayList;
import java.util.List;

public class NordicaTicketService implements AirlineTicketService{

	List<BoardingPass> passes = new ArrayList<>();
	
	public NordicaTicketService() {
		passes.add(new Ticket("John", "Smith", 453));
		passes.add(new Ticket("John", "Rawles", 2345));
		passes.add(new Ticket("Alan", "Turing", 394333));
	}
	
	@Override
	public boolean hasNextBoardingPass() {
		return !passes.isEmpty();
	}

	@Override
	public BoardingPass getNextBoardingPass() {
		return passes.remove(0);
	}

}
