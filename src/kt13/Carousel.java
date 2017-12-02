package kt13;

import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.concurrent.Callable;

public class Carousel implements Callable<Integer> {

    private String name;
    private TicketStorage ticketStorage;
    private int rideCount;
    private ArrayList<Ticket> waitingQueue = new ArrayList<>();
    private ArrayList<Ticket> usedTickets = new ArrayList<>();
    private double calculatedAverageWeight;

    Carousel(TicketStorage ticketStorage, String name) {
        this.ticketStorage = ticketStorage;
        this.name = name;
        this.ticketStorage.increment();
        this.rideCount = 0;
        this.calculatedAverageWeight = 0;
    }

    @Override
    public Integer call() throws Exception {

        ridePeople(500);

        calculateAverageWeight();

        if (calculatedAverageWeight <= 65) {
            ridePeople(700);
            calculateAverageWeight();
        }
        System.out.println("Ride count for " + name + ": " + rideCount);

        ticketStorage.decrement();
        return rideCount;
    }

    private void calculateAverageWeight() {
        OptionalDouble averageWeight = usedTickets.stream()
                .mapToInt(Ticket::getPersonWeight)
                .average();

        if (averageWeight.isPresent()) {
            calculatedAverageWeight = averageWeight.getAsDouble();
            System.out.println(name + ", used tickets: " + usedTickets.size()
                    + ", average weight: " + calculatedAverageWeight);
        }
    }

    private void ridePeople(int rideLimit) throws InterruptedException {
        while (rideCount < rideLimit) {
            Ticket ticket = ticketStorage.popTicket();
            waitingQueue.add(ticket);

            if (waitingQueue.size() == 10) {
                for (Ticket queueTicket : waitingQueue) {
                    rideCount++;
                    usedTickets.add(ticket);
                    System.out.println(name + " is ready for ticket number " + queueTicket.getTicketNumber());
                }
                Thread.sleep(67);
                waitingQueue.clear();
            }
        }
    }

}
