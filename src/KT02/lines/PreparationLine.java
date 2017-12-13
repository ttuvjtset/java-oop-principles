package KT02.lines;


import KT02.fruit.Fruit;
import KT02.fruit.FruitStorage;

import java.util.concurrent.Callable;

public class PreparationLine implements Callable<Integer> {
    private static final int COUNTER_INITIAL_NUMBER = 0;

    private String name;
    private int amount;
    private int time;
    private FruitStorage fruitStorage;

    public PreparationLine(String name, int amount, int time, FruitStorage fruitStorage) {
        this.name = name;
        this.amount = amount;
        this.time = time;
        this.fruitStorage = fruitStorage;
    }

    @Override
    public Integer call() throws Exception {
        int counter = COUNTER_INITIAL_NUMBER;

        while (counter < amount) {
            System.out.println("Preparing, cleaning, pealing " + name);

            fruitStorage.addFruit(new Fruit(name));

            counter++;
            Thread.sleep(time);
        }

        return counter;
    }
}
