package kodutoo11;

import kodutoo11.airport.AirportController;
import kodutoo11.storage.TicketArchive;
import kodutoo11.storage.TicketStorage;

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
