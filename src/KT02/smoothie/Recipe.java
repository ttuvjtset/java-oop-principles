package KT02.smoothie;

import KT02.fruit.Fruit;

import java.util.function.Predicate;

public class Recipe {
    private Predicate<? super Fruit> firstConditionInRecepy;
    private Predicate<? super Fruit> secondConditionInRecepy;
    private int firstConditionRepeatNumber;
    private int secondConditionRepeatNumber;

    public Predicate<? super Fruit> getFirstConditionInRecepy() {
        return firstConditionInRecepy;
    }

    public Predicate<? super Fruit> getSecondConditionInRecepy() {
        return secondConditionInRecepy;
    }

    public int getFirstConditionRepeatNumber() {
        return firstConditionRepeatNumber;
    }

    public int getSecondConditionRepeatNumber() {
        return secondConditionRepeatNumber;
    }

    public void setFirstConditionInRecepy(Predicate<? super Fruit> firstConditionInRecepy,
                                          int firstConditionRepeatNumber) {
        this.firstConditionInRecepy = firstConditionInRecepy;
        this.firstConditionRepeatNumber = firstConditionRepeatNumber;
    }

    public void setSecondConditionInRecepy(Predicate<? super Fruit> secondConditionInRecepy,
                                           int secondConditionRepeatNumber) {
        this.secondConditionInRecepy = secondConditionInRecepy;
        this.secondConditionRepeatNumber = secondConditionRepeatNumber;
    }


}
