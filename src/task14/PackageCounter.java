package task14;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class PackageCounter extends RecursiveTask<Long> {
    private List<Package> packages;

    PackageCounter(List<Package> packages) {
        this.packages = packages;
    }

    @Override
    protected Long compute() {
        if (packages.size() < 8) {
            // do work directly
            return countPackages(packages);

        } else {
            // devide work
            PackageCounter part1 = new PackageCounter(packages.subList(0, packages.size() / 2));
            PackageCounter part2 = new PackageCounter(packages.subList(packages.size() / 2, packages.size()));

            // fork first part (run in new thread)
            ForkJoinTask<Long> result1 = part1.fork(); // razdeljaet na otdelnij thread // see pool l2heb eraldi threadi
            // compute second part (run in the same thread)
            long result2 = part2.compute(); // << recursive task
            // aggregate (join blocks and waits until result is available)
            return result2 + result1.join();
        }
        //return null;
    }

    private Long countPackages(List<Package> packages) {
        // do actual counting here
        long weightSum = 0L;

        for (Package onePackage : packages) {
            weightSum += onePackage.getWeight();
        }
        return weightSum;
        // return 0L;
    }
}
