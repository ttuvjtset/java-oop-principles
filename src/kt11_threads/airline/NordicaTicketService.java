package kt11_threads.airline;

import kt11_threads.storage.TicketStorage;

import java.util.concurrent.atomic.AtomicInteger;

public class NordicaTicketService implements AirlineTicketService {

    private static final int MAX_TICKETS = 370;

    private TicketStorage storage;
    private AtomicInteger atomicInteger;

    public NordicaTicketService(TicketStorage storage, AtomicInteger atomicInteger) {
        this.storage = storage;
        this.atomicInteger = atomicInteger;
    }

    private int getUniqueID() {
        return atomicInteger.incrementAndGet();
    }

    private void generateTickets() {
        for (int i = 1; i <= MAX_TICKETS; i++) {
            BoardingPass ticket = new NordicaBoardingPass("John " + Thread.currentThread().getId(),
                    "Fu", getUniqueID());
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
