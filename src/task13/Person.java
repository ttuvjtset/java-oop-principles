package task13;


import java.util.Random;

public class Person {
    private int weight;

    public Person() {
        Random random = new Random();
        this.weight = random.nextInt(120 - 20 + 1) + 20;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Person{" +
                "weight=" + weight +
                '}';
    }
}
