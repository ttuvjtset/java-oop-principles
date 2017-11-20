package kt11_threads.securitygate;

import kt11_threads.storage.TicketsCollectorForArchive;

public class SecurityGateStatistics implements Runnable {

    private TicketsCollectorForArchive archive;
    private int gateID;

    public SecurityGateStatistics(TicketsCollectorForArchive archive, int gateID) {
        this.archive = archive;
        this.gateID = gateID;
    }

    private void printStatisticForGate() {
        Long countNum = archive.getTicketsCollectedForArchiving()
                .parallelStream()
                .filter(s -> s.getGateID() == gateID)
                .count();

        System.out.println("Statistics for Gate " + gateID + ". Total processed tickets: " + countNum +". ");

        archive.getTicketsCollectedForArchiving()
                .parallelStream()
                .filter(s -> s.getGateID() == gateID)
                .limit(1)
                .forEach(s -> System.out.println("Sample ticket from Gate " + gateID +": " + s));
    }

    @Override
    public void run() {
        printStatisticForGate();
    }
}