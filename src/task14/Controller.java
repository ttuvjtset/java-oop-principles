package task14;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AtomicInteger uniqueID = new AtomicInteger(1);

        ArrayList<Package> packages = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            packages.add(new Package(uniqueID));
            System.out.println(packages.size());
        }

        System.out.println(packages);

        int packagesTotalWeight = packages.parallelStream()
                .mapToInt(Package::getWeight)
                .sum();

        System.out.println("Packages total weight: " + packagesTotalWeight);

        ForkJoinPool pool = new ForkJoinPool(4);

        //ForkJoinTask<Long> countPackagesTask = new PackageCounter(aPackages);

        //pool.execute(countPackagesTask);

        long sumWeight = pool.invoke(new PackageCounter(packages));
        System.out.println("calculated with fork/join: " + sumWeight);

        // long temp = 0L;


        CompletableFuture // javascritptis promise, chtoto delajetsja kakojeto vremja, potom poluchajetsja rezultat
                //+			// job that is done asynchronously
                //.supplyAsync(Details::new) // delajet novij thread, massivnija operacija, rjad, kotoij chto delajet
                // < async, ne zdjot poka sdelajet!
                .supplyAsync(() -> new SmallestPackageFinder(packages))
                //+			// when this job is finised, do this action with a result
                .thenAccept(d -> System.out.println(d.findSmallest()));
    }


}
