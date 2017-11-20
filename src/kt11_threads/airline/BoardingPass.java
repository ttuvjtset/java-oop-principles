package kt11_threads.airline;

public interface BoardingPass {
    String getPassengerFirstName();

    String getPassengerLastName();

    long getTicketCode();

    void setProcessedByGateID(int gateID);

    int getGateID();
}
