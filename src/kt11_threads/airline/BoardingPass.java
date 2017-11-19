package kt11_threads.airline;

public interface BoardingPass {
    public String getPassengerFirstName();

    public String getPassengerLastName();

    public long getTicketCode();

    public void setProcessedByGateID(int gateID);

    public int getGateID();
}
