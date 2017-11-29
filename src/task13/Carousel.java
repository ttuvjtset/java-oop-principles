package task13;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.concurrent.Callable;

public class Carousel implements Callable<Integer> {
    private Tickets tickets;
    private int count = 0;

    Carousel(Tickets tickets) {
        this.tickets = tickets;
    }


    @Override
    public Integer call() throws Exception {
        while (Tickets.stopped.size() != 3) {
            try {
                System.out.println(tickets.popTicket());
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Tickets.stopped.add(this);
        return count;
    }
}
