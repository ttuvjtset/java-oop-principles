package task14;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Package {
    private int id;
    private int weight;

    Package(AtomicInteger atomicInteger) {
        this.id = atomicInteger.getAndIncrement();
        this.weight = new Random().nextInt(1500 - 30) + 30;
    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", weight=" + weight +
                '}';
    }
}
