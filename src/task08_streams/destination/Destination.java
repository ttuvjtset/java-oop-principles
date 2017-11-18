package task08_streams.destination;

import java.util.function.DoubleFunction;

public class Destination implements DestinationModel {

    private String name;
    private double averageTemp;

    public Destination(String name, double averageTemp) {
        this.name = name;
        this.averageTemp = averageTemp;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getKelvin() {
        return averageTemp;
    }

    @Override
    public double getAvgWeather(DoubleFunction<Double> tempHandler) {
        return tempHandler.apply(getKelvin());
    }

    public double getCelsius() {
        return getKelvin() - 273.15;
    }

    public double getFahrenheit() {
        return (getKelvin() * 9/5) - 459.67;
    }
}
