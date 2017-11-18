package kodutoo11.securitygate;

import kodutoo11.airline.BoardingPass;
import kodutoo11.storage.TicketArchive;
import kodutoo11.storage.TicketStorage;

import java.time.LocalDateTime;
import java.util.Optional;

public class SecurityGate implements SecurityGateDatabase {

    private TicketStorage storage;

    private TicketArchive archive;
    private int gateID;

    public SecurityGate(TicketStorage storage, TicketArchive archive, int gateID) {
        this.storage = storage;
        this.archive = archive;
        this.gateID = gateID;
    }

    public int getGateID() {
        return gateID;
    }

    private void processTicket(BoardingPass boardingPass) {
        System.out.println("Registreeritud pardakaart (Gate: " + getGateID() + ") nr: "
                + boardingPass.getTicketCode()
                + ": "
                + boardingPass.getPassengerFirstName()
                + " "
                + boardingPass.getPassengerLastName()
                + " "
                + LocalDateTime.now().toString());
    }

    private void fetchTickets() throws InterruptedException {
        while (!Thread.interrupted()) {
            Optional<BoardingPass> ticket = storage.getTicket();
//            ticket.ifPresent(this::processTicket);
            if (ticket.isPresent()) {
				processTicket(ticket.get());
				archive.addTicket(ticket.get());
			}
            Thread.sleep(50);

        }
    }

    /*
     * This thread finishes when fetchTickets() finishes
     * but this is more tricky, because...
     * ... fetchTickets() finishes only when thread is
     * interrupted (you have to interrupt it from your code,
     * it our case from controller)
     * */
    @Override
    public void run() {
        try {
            fetchTickets();
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
    }
}




