package task14;

import java.util.ArrayList;
import java.util.OptionalInt;

class PackageSplitter {
    private ArrayList<Package> packages = new ArrayList<>();

    PackageSplitter(ArrayList<Package> packages) {
        this.packages = packages;
    }

    int findSmallest() {
        OptionalInt optionalInt = packages.parallelStream().mapToInt(Package::getWeight).min();

        if (optionalInt.isPresent()) {
            return optionalInt.getAsInt();
        }
        return 0;
    }
}
