package task13;

import java.util.concurrent.atomic.AtomicInteger;

public class PersonProducer extends Person {
    PersonProducer(String name, Board board, AtomicInteger atomicInteger) {
        super(name, board, atomicInteger);
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {
                Thread.sleep(200);

                Message message = new Message("hello" + atomicInteger.incrementAndGet(), this);

                super.board.addMessage(message);
                System.out.println("Message: " + message.getMessageContent() + " written by: " + message.getAuthorsName());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
