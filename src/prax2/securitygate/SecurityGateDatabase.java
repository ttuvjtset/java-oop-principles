package prax2.securitygate;

import _kruglovaa.prax2.airline.BoardingPass;

public interface SecurityGateDatabase {
	public void registerTicket(BoardingPass boardingPass) throws IllegalArgumentException;
}
