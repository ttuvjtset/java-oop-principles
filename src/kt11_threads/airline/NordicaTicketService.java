package kt11_threads.airline;

import kt11_threads.storage.TicketStorage;

public class NordicaTicketService implements AirlineTicketService {

    private static final int MAX_TICKETS = 370;
    private static int id = 1;
    private TicketStorage storage;

    public NordicaTicketService(TicketStorage storage) {
        this.storage = storage;
    }

    private synchronized static int getUniqueID() {
        return id++;
    }

    private void generateTickets() {
        for (int i = 1; i <= MAX_TICKETS; i++) {
            BoardingPass ticket = new NordicaBoardingPass("John " + Thread.currentThread().getId(), "Fu", getUniqueID());
            storage.addTicket(ticket);
        }
    }

    /*
     * This thread finishes when generateTickets() finishes
     * */
    @Override
    public void run() {
        generateTickets();
    }
}
