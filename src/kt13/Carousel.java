package kt13;

import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.concurrent.Callable;

public class Carousel implements Callable<Integer> {

    private TicketStorage ticketStorage;
    private String name;
    private TotalWeight totalWeight;
    private int rideCount;
    private ArrayList<Ticket> waitingQueue = new ArrayList<>();
    private ArrayList<Ticket> usedTickets = new ArrayList<>();
    private double calculatedAverageWeight;
    private Flag flag;

    Carousel(TicketStorage ticketStorage, String name, TotalWeight totalWeight, Flag flag) {
        this.ticketStorage = ticketStorage;
        this.name = name;
        this.totalWeight = totalWeight;
        this.ticketStorage.increment();
        this.rideCount = 0;
        this.calculatedAverageWeight = 0;
        this.flag = flag;
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
        while (rideCount < rideLimit && flag.isRideAllowed()) {
            Ticket ticket = ticketStorage.popTicket();
            waitingQueue.add(ticket);

            if (waitingQueue.size() == 10) {
                int sumWeight = waitingQueue.stream().mapToInt(Ticket::getPersonWeight).sum();

                totalWeight.registerWeight(sumWeight);

                for (Ticket queueTicket : waitingQueue) {
                    rideCount++;
                    usedTickets.add(ticket);
                    System.out.println(name + " is ready for ticket number " + queueTicket.getTicketNumber());
                }
                Thread.sleep(67);
                totalWeight.removeWeight(sumWeight);
                waitingQueue.clear();
            }
        }
    }

}
