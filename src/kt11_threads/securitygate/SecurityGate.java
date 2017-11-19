package kt11_threads.securitygate;

import kt11_threads.airline.BoardingPass;
import kt11_threads.storage.TicketArchive;
import kt11_threads.storage.TicketStorage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("hh:mm:ss");

        System.out.println("Registreeritud pardakaart (Gate: " + getGateID() + ") nr: "
                + boardingPass.getTicketCode()
                + ": "
                + boardingPass.getPassengerFirstName()
                + " "
                + boardingPass.getPassengerLastName()
                + " "
                + LocalTime.now().format(formatter));
    }

    private void fetchTickets() throws InterruptedException {
        while (!Thread.interrupted()) {
            Optional<BoardingPass> ticket = storage.getTicket();
//            ticket.ifPresent(this::processTicket);
            if (ticket.isPresent()) {
				processTicket(ticket.get());
				ticket.get().setProcessedByGateID(gateID);
				archive.addTicket(ticket.get());
			}
            //Thread.sleep(50);

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




