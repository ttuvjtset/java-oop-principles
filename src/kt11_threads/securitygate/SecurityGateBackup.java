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

        //System.out.println(gateRunners);


        for (Thread gateRunner : gateRunners) {
//            System.out.println(gateRunner);
//            System.out.println(gateRunner.isAlive());
//            System.out.println(gateRunner.isInterrupted());
            //System.out.println("backup :" + gateRunner.isInterrupted() + " " + gateRunner.isAlive());

            if (!gateRunner.isAlive()) {
                System.out.println("Ground service is stopped");
            }
        }
    }
}
