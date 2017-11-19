package _martin.prax02.securitygate;

import _martin.prax02.airline.BoardingPass;

public interface SecurityGateDatabase {
	public void registerTicket(BoardingPass boardingPass) throws IllegalArgumentException;
}
