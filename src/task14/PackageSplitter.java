package task14;

import java.util.ArrayList;
import java.util.OptionalInt;

public class PackageSplitter {
    ArrayList<Package> packageArrayList = new ArrayList<>();

    public PackageSplitter(ArrayList<Package> packageArrayList) {
        this.packageArrayList = packageArrayList;
    }

    public int  findSmallest() {
        OptionalInt optionalInt = packageArrayList.parallelStream().mapToInt(Package::getWeight).min();

        if (optionalInt.isPresent()) {
            return optionalInt.getAsInt();
        } else {
            return 0;
        }
    }
}
