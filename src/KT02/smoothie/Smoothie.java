package KT02.smoothie;

import KT02.fruit.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Smoothie {
    private static final double PRICE_FOR_NORMAL_SMOOTHIE = 0.99;

    private List<Fruit> smoothieComponents = new ArrayList<>();
    private double price;

    public Smoothie() {
        this.price = PRICE_FOR_NORMAL_SMOOTHIE;
    }

    public List<Fruit> getSmoothieComponents() {
        return smoothieComponents;
    }

    public void addComponents(List<Fruit> fruitComponents) {
        smoothieComponents.addAll(fruitComponents);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Smoothie{" +
                "smoothieComponents=" + smoothieComponents +
                ", price=" + price +
                '}';
    }
}
