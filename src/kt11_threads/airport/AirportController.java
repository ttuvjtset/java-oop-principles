package kt11_threads.airport;

import kt11_threads.airline.AirlineTicketService;
import kt11_threads.airline.NordicaTicketService;
import kt11_threads.securitygate.SecurityGate;
import kt11_threads.securitygate.SecurityGateBackup;
import kt11_threads.securitygate.SecurityGateDatabase;
import kt11_threads.securitygate.SecurityGateStatistics;
import kt11_threads.storage.TicketArchive;
import kt11_threads.storage.TicketStorage;

import java.util.Arrays;
import java.util.List;

/*
 * we refactored controller to 
 * start up the system
 * controller does not process tickets any more
 * 
 * */
public class AirportController {

    private TicketStorage storage;
    private TicketArchive archive;

    public AirportController(TicketStorage storage, TicketArchive archive) {
        this.storage = storage;
        this.archive = archive;
    }

    public void runAirport() throws InterruptedException {
        startNewTicketService();
        startNewTicketService();

        Thread gateRunner1 = startAndReturnNewGate(1);
        Thread gateRunner2 = startAndReturnNewGate(2);

        List<Thread> gateRunners = Arrays.asList(gateRunner1, gateRunner2);

        SecurityGateDatabase gateBackup = new SecurityGateBackup(gateRunners);
        Thread gateRunnerBackup = new Thread(gateBackup);
        gateRunnerBackup.start();
        //gateRunnerBackup.wait();

        Thread.sleep(10_000);

//        System.out.println(gateRunner1.isInterrupted() + " " + gateRunner1.isAlive());
//        System.out.println(gateRunner2.isInterrupted() + " " + gateRunner2.isAlive());

        gateRunner1.interrupt();
        gateRunner2.interrupt();

//        System.out.println(gateRunner1 + " " + gateRunner1.isInterrupted() + " " + gateRunner1.isAlive());
//        System.out.println(gateRunner2 + " " + gateRunner2.isInterrupted() + " " + gateRunner2.isAlive());

        Thread statisticsForGate1 = printStatisticForGate(1);
        statisticsForGate1.join();
        Thread statisticsForGate2 = printStatisticForGate(2);
        statisticsForGate2.join();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");



        // System.out.println(archive.getArchivedPasses().toString());
        // System.out.println(archive.getArchivedPasses().size());
    }

    private Thread printStatisticForGate(int gateID) {
        SecurityGateStatistics securityGateStatistics = new SecurityGateStatistics(archive, gateID);
        Thread securityGateStatisticsThread = new Thread(securityGateStatistics);
        securityGateStatisticsThread.start();
        return securityGateStatisticsThread;
    }

    private Thread startAndReturnNewGate(int gateID) {
        SecurityGateDatabase gate = new SecurityGate(storage, archive, gateID);
        Thread gateRunner = new Thread(gate);
        gateRunner.start();
        return gateRunner;
    }

    private void startNewTicketService() {
        AirlineTicketService ticketService = new NordicaTicketService(storage);
        Thread nordicaRunner = new Thread(ticketService);
        nordicaRunner.start();
    }
}







