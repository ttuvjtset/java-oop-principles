package _martinn.prax02.securitygate;

import _kruglovaa.prax2.airline.BoardingPass;

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
