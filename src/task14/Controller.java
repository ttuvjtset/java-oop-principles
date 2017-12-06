package task14;

import bank_forkjoin_example.CoinCounter;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {


    public static void main(String[] args) {
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

    }

}
