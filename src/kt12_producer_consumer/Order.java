package kt12_producer_consumer;


import java.util.function.DoubleFunction;

public interface Order {
    String getName();

    String getAddress();

    int getClientNumber();

    int getOrderLengthInMonths();

    int getUnitsOrdered();

    int getUnitsPerWeek();

    double getPrice(DoubleFunction<Double> tempHandler);
}
