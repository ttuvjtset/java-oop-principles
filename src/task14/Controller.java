package task14;

import bank_forkjoin_example.CoinCounter;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AtomicInteger uniqueID = new AtomicInteger(1);

        ArrayList<Package> aPackages = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            aPackages.add(new Package(uniqueID));
            System.out.println(aPackages.size());
        }

        System.out.println(aPackages);

        int packagesTotalWeight = aPackages.parallelStream()
                .mapToInt(Package::getWeight)
                .sum();

        System.out.println("Packages total weight: " + packagesTotalWeight);

        ForkJoinPool pool = new ForkJoinPool(4);

        //ForkJoinTask<Long> countPackagesTask = new PackageCounter(aPackages);

        //pool.execute(countPackagesTask);

        long sumWeight = pool.invoke(new PackageCounter(aPackages));
        System.out.println("calculated with fork/join: " + sumWeight);

        long temp = 0L;


        CompletableFuture // javascritptis promise, chtoto delajetsja kakojeto vremja, potom poluchajetsja rezultat
                //+			// job that is done asynchronously
 			//.supplyAsync(Details::new) // delajet novij thread, massivnija operacija, rjad, kotoij chto delajet < async, ne zdjot poka sdelajet!
                .supplyAsync(() -> new PackageSplitter(aPackages))
                //+			// when this job is finised, do this action with a result
 			.thenAccept(d -> System.out.println(d.findSmallest()));
    }



}
