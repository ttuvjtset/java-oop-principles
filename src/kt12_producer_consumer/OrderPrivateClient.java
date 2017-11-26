package kt12_producer_consumer;


public class OrderPrivateClient implements Order {
    private static final int STANDARD_UNIT_AMOUNT_FOR_PRIVATE_CLIENTS_PER_WEEK = 6;
    private String name;
    private String address;
    private int clientNumber;
    private int orderLengthInMonths;
    private int unitsOrdered;

    public OrderPrivateClient(String name, String address, int clientNumber, int orderLengthInMonths, int unitsOrdered) {
        this.name = name;
        this.address = address;
        this.clientNumber = clientNumber;
        this.orderLengthInMonths = orderLengthInMonths;
        this.unitsOrdered = unitsOrdered;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public int getOrderLengthInMonths() {
        return orderLengthInMonths;
    }

    public int getUnitsOrdered() {
        return unitsOrdered;
    }

    public int getUnitsPerWeek() {
        return getUnitsOrdered() * STANDARD_UNIT_AMOUNT_FOR_PRIVATE_CLIENTS_PER_WEEK;
    }

    public int calculatePrice() {
        return 0;
    }

    @Override
    public String toString() {
        return "OrderPrivateClient{" +
                  name + '\'' +
                  address + '\'' +
                ", clientNumber=" + clientNumber +
                ", orderLengthInMonths=" + orderLengthInMonths +
                ", unitsOrdered=" + unitsOrdered +
                '}';
    }
}
