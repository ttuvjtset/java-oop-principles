package kt11_threads.securitygate;


import java.util.List;

public class SecurityGateBackup implements SecurityGateDatabase {

    private volatile List<Thread> gateRunners;

    public SecurityGateBackup(List<Thread> gateRunners) {
        this.gateRunners = gateRunners;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(20_000);
            System.out.println("Backup Gate 20 sec exceeded, checking if the gates still work.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Thread gateRunner : gateRunners) {
            if (!gateRunner.isAlive()) {
                System.out.println("Ground service is stopped");
            }
        }
    }
}
