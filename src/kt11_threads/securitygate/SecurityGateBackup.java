package kt11_threads.securitygate;


import java.util.List;

public class SecurityGateBackup implements SecurityGateDatabase {
    private List<Thread> gateRunners;

    public SecurityGateBackup(List<Thread> gateRunners) {
        this.gateRunners = gateRunners;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(20_000);
            System.out.println("eee");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(gateRunners);


        for (Thread gateRunner : gateRunners) {
//            System.out.println(gateRunner);
//            System.out.println(gateRunner.isAlive());
//            System.out.println(gateRunner.isInterrupted());
            if (!gateRunner.isAlive()) {
                System.out.println("Ground service is stopped");
            }
        }
    }
}
