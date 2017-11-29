package task13;

import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {
    private Person person;
    private int id;

    Ticket(AtomicInteger uniqueTicketID) {
        id = uniqueTicketID.getAndIncrement();
    }

    public int getId() {
        return id;
    }

    void assignToPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "person=" + person +
                ", id=" + id +
                '}';
    }
}
