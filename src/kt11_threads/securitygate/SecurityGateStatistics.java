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
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Statistics for Gate " + gateID);

        Long countNum = archive.getTicketsCollectedForArchiving()
                .parallelStream()
                .filter(s -> s.getGateID() == gateID)
                .count();
        System.out.println("Total tickets at Gate " + gateID + ": " + countNum);

        archive.getTicketsCollectedForArchiving()
                .parallelStream()
                .filter(s -> s.getGateID() == gateID)
                .limit(1)
                .forEach(System.out::println);
    }

    @Override
    public void run() {
        printStatisticForGate();
    }
}