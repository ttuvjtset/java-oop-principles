package kt12_producer_consumer;


public interface Order {
    String getName();
    String getAddress();
    int getClientNumber();
    int getOrderLengthInMonths();
    int getUnitsOrdered();
    int getUnitsPerWeek();
    int calculatePrice();
}
