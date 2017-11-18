package example02.securitygate;

import example02.airline.BoardingPass;

public interface SecurityGateDatabase {
	public void registerTicket(BoardingPass boardingPass) throws IllegalArgumentException;
}
