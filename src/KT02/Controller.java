package KT02;

import KT02.fruit.FruitStorage;
import KT02.lines.PreparationLine;
import KT02.lines.ProductionLine;
import KT02.smoothie.Recipe;
import KT02.smoothie.Smoothie;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Controller {

    private static final int THREAD_NUMBER = 5;
    private static final int AMOUNT_TO_PRODUCE = 300;

    private static final double PRICE_FOR_DELICIOUS_SMOOTHIE = 1.20;

    private static final int FIRST_CONDITION_REPEAT_NUMBER_RECEPY_ONE = 2;
    private static final int SECOND_CONDITION_REPEAT_NUMBER_RECEPY_ONE = 1;
    private static final int FIRST_CONDITION_REPEAT_NUMBER_RECEPY_TWO = 2;
    private static final int SECOND_CONDITION_REPEAT_NUMBER_RECEPY_TWO = 1;

    private static final int FRUIT_ONE_AMOUNT = 900;
    private static final int FRUIT_TWO_AMMOUNT = 300;
    private static final int FRUIT_THREE_AMOUNT = 600;

    private static final String FRUIT_ONE = "Carrot";
    private static final String FRUIT_TWO = "Mango";
    private static final String FRUIT_THREE = "Yellow Plum";

    private static final int PREPARATION_TIME_FRUIT_ONE = 4;
    private static final int PREPARATION_TIME_FRUIT_TWO = 7;
    private static final int PREPARATION_TIME_FRUIT_THREE = 5;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FruitStorage fruitStorage = new FruitStorage();

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBER);

        // used futures for preparation lines only for debugging purposes
        executor.submit(new PreparationLine(FRUIT_ONE, FRUIT_ONE_AMOUNT, PREPARATION_TIME_FRUIT_ONE, fruitStorage));
        executor.submit(new PreparationLine(FRUIT_TWO, FRUIT_TWO_AMMOUNT, PREPARATION_TIME_FRUIT_TWO, fruitStorage));
        executor.submit(new PreparationLine(FRUIT_THREE, FRUIT_THREE_AMOUNT, PREPARATION_TIME_FRUIT_THREE,
                fruitStorage));


        Recipe recipeOne = new Recipe();
        recipeOne.setFirstConditionInRecepy(fruit -> fruit.getName().equals(FRUIT_ONE),
                FIRST_CONDITION_REPEAT_NUMBER_RECEPY_ONE);
        recipeOne.setSecondConditionInRecepy(fruit -> fruit.getName().equals(FRUIT_TWO),
                SECOND_CONDITION_REPEAT_NUMBER_RECEPY_ONE);

        Recipe recipeTwo = new Recipe();
        recipeTwo.setFirstConditionInRecepy(fruit -> fruit.getName().equals(FRUIT_THREE),
                FIRST_CONDITION_REPEAT_NUMBER_RECEPY_TWO);
        recipeTwo.setSecondConditionInRecepy(fruit -> fruit.getName().equals(FRUIT_ONE),
                SECOND_CONDITION_REPEAT_NUMBER_RECEPY_TWO);

        Future<List<Smoothie>> productionLine1 = executor.submit(new ProductionLine(AMOUNT_TO_PRODUCE, fruitStorage,
                recipeOne, smoothie -> {
            smoothie.setPrice(PRICE_FOR_DELICIOUS_SMOOTHIE);
            System.out.println("Price set!");
        }));

        Future<List<Smoothie>> productionLine2 = executor.submit(new ProductionLine(AMOUNT_TO_PRODUCE, fruitStorage,
                recipeTwo, System.out::println));

        System.out.println("Production Line 1 produced: " + productionLine1.get().size());
        System.out.println("Production Line 2 produced: " + productionLine2.get().size());

        executor.shutdownNow();
    }
}
