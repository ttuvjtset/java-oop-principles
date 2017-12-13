package KT02.lines;

import KT02.fruit.Fruit;
import KT02.fruit.FruitStorage;
import KT02.smoothie.Recipe;
import KT02.smoothie.Smoothie;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class ProductionLine implements Callable<List<Smoothie>> {
    private static final int COUNTER_INITIAL_NUMBER = 0;

    private Consumer<? super Smoothie> operationOnEachSmoothie;
    private List<Smoothie> smoothiesList = new ArrayList<>();
    private int amountToProduce;
    private FruitStorage fruitStorage;
    private Recipe recipeInstructions;


    public ProductionLine(int amountToProduce, FruitStorage fruitStorage, Recipe recipeInstructions,
                          Consumer<? super Smoothie> operationOnEachSmoothie) {
        this.amountToProduce = amountToProduce;
        this.fruitStorage = fruitStorage;
        this.recipeInstructions = recipeInstructions;
        this.operationOnEachSmoothie = operationOnEachSmoothie;
    }

    @Override
    public List<Smoothie> call() throws Exception {
        int counter = COUNTER_INITIAL_NUMBER;

        while (counter < amountToProduce) {
            System.out.println("Producing smoothie");

            List<Fruit> fruitFirstComponent = fruitStorage.popFruitsIfExists(
                    recipeInstructions.getFirstConditionInRecepy(), recipeInstructions.getFirstConditionRepeatNumber());

            List<Fruit> fruitSecondComponent = fruitStorage.popFruitsIfExists(
                    recipeInstructions.getSecondConditionInRecepy(),
                    recipeInstructions.getSecondConditionRepeatNumber());

            Smoothie smoothie = new Smoothie();
            smoothie.addComponents(fruitFirstComponent);
            smoothie.addComponents(fruitSecondComponent);

            List<Smoothie> smoothieProduced = new ArrayList<>();
            smoothieProduced.add(smoothie);
            smoothieProduced.forEach(operationOnEachSmoothie);

            smoothiesList.add(smoothie);

            counter++;
        }

        return smoothiesList;

    }
}
