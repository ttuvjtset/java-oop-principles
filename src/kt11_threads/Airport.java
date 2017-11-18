package kt11_threads;

import kt11_threads.airport.AirportController;
import kt11_threads.storage.TicketArchive;
import kt11_threads.storage.TicketStorage;

public class Airport {
    public static void main(String[] args) {

        TicketStorage storage = new TicketStorage();
        TicketArchive archive = new TicketArchive();

        AirportController controller = new AirportController(storage, archive);

        try {
            controller.runAirport();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
