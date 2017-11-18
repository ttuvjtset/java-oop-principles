package kodutoo11.airport;

import kodutoo11.airline.AirlineTicketService;
import kodutoo11.airline.NordicaTicketService;
import kodutoo11.securitygate.SecurityGate;
import kodutoo11.securitygate.SecurityGateBackup;
import kodutoo11.securitygate.SecurityGateDatabase;
import kodutoo11.storage.TicketArchive;
import kodutoo11.storage.TicketStorage;

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

        Thread.sleep(19_000);

        gateRunner1.interrupt();
        gateRunner2.interrupt();

        // System.out.println(archive.getArchivedPasses().toString());
        // System.out.println(archive.getArchivedPasses().size());
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







