package task13;

public class Carousel implements Runnable {
    private Tickets tickets;
    private int count = 0;

    Carousel(Tickets tickets) {
        this.tickets = tickets;
    }

    @Override
    public void run() {
        while (count < 5000) {
            try {
                System.out.println(tickets.popTicket());
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
