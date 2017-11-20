package kt11_threads.airport;

import kt11_threads.airline.AirlineTicketService;
import kt11_threads.airline.NordicaTicketService;
import kt11_threads.securitygate.SecurityGate;
import kt11_threads.securitygate.SecurityGateBackup;
import kt11_threads.securitygate.SecurityGateDatabase;
import kt11_threads.securitygate.SecurityGateStatistics;
import kt11_threads.storage.TicketOfflineArchive;
import kt11_threads.storage.TicketStorage;
import kt11_threads.storage.TicketsCollectorForArchive;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * we refactored controller to 
 * start up the system
 * controller does not process tickets any more
 * 
 * */
public class AirportController {

    private TicketStorage storage;
    private TicketsCollectorForArchive ticketsCollectorForArchive;
    private AtomicInteger atomicInteger;

    public AirportController(TicketStorage storage) {
        this.storage = storage;
        this.atomicInteger = new AtomicInteger(0);
    }

    public void runAirport() throws InterruptedException {
        Thread ticketCollector = startTicketsArchiveService();

        startNewTicketService();
        startNewTicketService();

        Thread gateRunner1 = startAndReturnNewGate(1);
        Thread gateRunner2 = startAndReturnNewGate(2);

        startBackupGate(gateRunner1, gateRunner2);

        Thread.sleep(10_000);

        gateRunner1.interrupt();
        gateRunner2.interrupt();
        ticketCollector.interrupt();

        printStatisticForGate(1);
        printStatisticForGate(2);
    }

    private void startBackupGate(Thread gateRunner1, Thread gateRunner2) {
        List<Thread> gateRunners = Arrays.asList(gateRunner1, gateRunner2);

        SecurityGateDatabase gateBackup = new SecurityGateBackup(gateRunners);
        Thread gateRunnerBackup = new Thread(gateBackup);
        gateRunnerBackup.start();
    }

    private Thread startTicketsArchiveService() {
        TicketOfflineArchive ticketOfflineArchive = new TicketOfflineArchive();
        ticketsCollectorForArchive = new TicketsCollectorForArchive(ticketOfflineArchive);
        Thread ticketCollector = new Thread(ticketsCollectorForArchive);
        ticketCollector.start();
        return ticketCollector;
    }

    private void printStatisticForGate(int gateID) {
        SecurityGateStatistics securityGateStatistics = new SecurityGateStatistics(ticketsCollectorForArchive, gateID);
        Thread securityGateStatisticsThread = new Thread(securityGateStatistics);
        securityGateStatisticsThread.start();
    }

    private Thread startAndReturnNewGate(int gateID) {
        SecurityGateDatabase gate = new SecurityGate(storage, ticketsCollectorForArchive, gateID);
        Thread gateRunner = new Thread(gate);
        gateRunner.start();
        return gateRunner;
    }

    private void startNewTicketService() {
        AirlineTicketService ticketService = new NordicaTicketService(storage, atomicInteger);
        Thread nordicaRunner = new Thread(ticketService);
        nordicaRunner.start();
    }
}







