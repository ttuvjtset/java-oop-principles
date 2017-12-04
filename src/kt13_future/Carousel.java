package kt13_future;

import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.concurrent.Callable;

public class Carousel implements Callable<Double> {

    private static final int WEIGHT_THRESHOLD_FOR_EXTRA_DUTY = 65;
    private static final int QUEUE_LIMIT = 10;
    private static final int CAROUSEL_SPINNING_TIME = 67;
    private static final int USUAL_AMOUNT_OF_PEOPLE = 500;
    private static final int EXTRA_AMOUNT_OF_PEOPLE = 700;

    private TicketStorage ticketStorage;
    private String name;
    private TotalWeight totalWeight;
    private int rideCount;
    private ArrayList<Ticket> waitingQueue = new ArrayList<>();
    private ArrayList<Ticket> usedTickets = new ArrayList<>();
    private double calculatedAverageWeight;
    private volatile Flag flag;
    //private volatile boolean stop;


    Carousel(TicketStorage ticketStorage, String name, TotalWeight totalWeight, Flag flag, boolean stop) {
        this.ticketStorage = ticketStorage;
        this.name = name;
        this.totalWeight = totalWeight;
        this.ticketStorage.increment();
        this.rideCount = 0;
        this.calculatedAverageWeight = 0;
        this.flag = flag;
        //this.stop = stop;
    }

    @Override
    public Double call() throws Exception {

        ridePeople(USUAL_AMOUNT_OF_PEOPLE);

        calculateAverageWeightAndPrintOut();

        if (isEligibleForExtraDuty()) {
            ridePeople(EXTRA_AMOUNT_OF_PEOPLE);
            calculateAverageWeightAndPrintOut();
        }
        System.out.println("Ride count for " + name + ": " + rideCount);

        ticketStorage.decrement();
        return calculatedAverageWeight;
    }

    private boolean isEligibleForExtraDuty() {
        return calculatedAverageWeight <= WEIGHT_THRESHOLD_FOR_EXTRA_DUTY;
    }

    private void calculateAverageWeightAndPrintOut() {
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

            if (waitingQueue.size() == QUEUE_LIMIT) {
                int sumWeight = waitingQueue.stream().mapToInt(Ticket::getPersonWeight).sum();

                totalWeight.registerWeight(sumWeight);

                for (Ticket queueTicket : waitingQueue) {
                    rideCount++;
                    usedTickets.add(ticket);
                    System.out.println(name + " is ready for ticket number " + queueTicket.getTicketNumber());
                }
                Thread.sleep(CAROUSEL_SPINNING_TIME);
                totalWeight.removeWeight(sumWeight);
                waitingQueue.clear();
            }
        }
    }

}
