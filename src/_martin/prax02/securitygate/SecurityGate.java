package _martin.prax02.securitygate;

import _martin.prax02.airline.BoardingPass;

public class SecurityGate implements SecurityGateDatabase{

	@Override
	public void registerTicket(BoardingPass boardingPass) throws IllegalArgumentException {
		System.out.println("Registreeritud pardakaart nr: "
				+ boardingPass.getTicketCode()
				+ ": "
				+ boardingPass.getPassengerFirstName()
				+ " "
				+ boardingPass.getPassengerLastName());
	}

}
