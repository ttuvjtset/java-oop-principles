package _martin.prax13;

import java.util.Random;

public class Ticket {

    private static final int MIN_WEIGHT = 20;
    private static final int MAX_WEIGHT = 120;
    private int ticketNumber;
    private int personWeight;
    private Random random = new Random();

    public Ticket() {
        this.ticketNumber = random.nextInt(99) + 1;
        this.personWeight = random.nextInt(MAX_WEIGHT - MIN_WEIGHT) + MIN_WEIGHT;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public int getPersonWeight() {
        return personWeight;
    }
}
