package KT02.fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FruitStorage {
    private final List<Fruit> fruitComponents = new ArrayList<>();

    public List<Fruit> getFruitComponents() {
        return fruitComponents;
    }

    public List<Fruit> popFruitsIfExists(Predicate<? super Fruit> recipyCondition, int repeatNumber) {

        synchronized (fruitComponents) {

            List<Fruit> fruitInContainer = new ArrayList<>();

            while (fruitInContainer.size() != repeatNumber) {

                fruitInContainer = fruitComponents.stream().filter(recipyCondition).limit(repeatNumber)
                        .collect(Collectors.toList());

                if (fruitInContainer.size() != repeatNumber) {
                    try {
                        fruitComponents.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Shutting down sleeping client thread");
                        break;
                    }
                }

            }
            fruitComponents.removeAll(fruitInContainer);

            return fruitInContainer;
        }
    }

    public void addFruit(Fruit fruitComponent) {
        synchronized (fruitComponents) {
            fruitComponents.add(fruitComponent);
            fruitComponents.notifyAll();
        }
    }
}
