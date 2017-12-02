package kt13;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {

    private int ticketNumber;
    private int personWeight;
    private static final int MIN_WEIGHT = 20;
    private static final int MAX_WEIGHT = 120;

    public Ticket(AtomicInteger uniqueTicketIDs) {
        this.ticketNumber = uniqueTicketIDs.getAndIncrement();
        this.personWeight = new Random().nextInt(MAX_WEIGHT - MIN_WEIGHT) + MIN_WEIGHT;
    }

    int getTicketNumber() {
        return ticketNumber;
    }

    public int getPersonWeight() {
        return personWeight;
    }
}
