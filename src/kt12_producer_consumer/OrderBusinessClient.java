package kt12_producer_consumer;


public class OrderBusinessClient extends OrderPrivateClient {
    private static final int STANDARD_UNIT_AMOUNT_FOR_BUSINESS_CLIENTS_PER_WEEK = 5;
    private int registerCode;

    OrderBusinessClient(String name, String address, int clientNumber, int orderLengthInMonths, int unitsOrdered,
                        int registerCode) {
        super(name, address, clientNumber, orderLengthInMonths, unitsOrdered);
        this.registerCode = registerCode;
    }

    public int getRegisterCode() {
        return registerCode;
    }

    public int getUnitsPerWeek() {
        return getUnitsOrdered() * STANDARD_UNIT_AMOUNT_FOR_BUSINESS_CLIENTS_PER_WEEK;
    }

    @Override
    public String toString() {
        return "OrderBusinessClient{" +
                super.getName() + '\'' +
                super.getAddress() + '\'' +
                ", clientNumber=" + super.getClientNumber() +
                ", orderLengthInMonths=" + super.getOrderLengthInMonths() +
                ", unitsOrdered=" + super.getUnitsOrdered() +
                ", registerCode=" + registerCode +
                '}';
    }
}
