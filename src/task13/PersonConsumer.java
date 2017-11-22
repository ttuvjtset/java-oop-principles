package task13;

import java.util.concurrent.atomic.AtomicInteger;

public class PersonConsumer extends Person {
    private Message lastLikedMessage;

    PersonConsumer(String name, Board board, AtomicInteger atomicInteger) {
        super(name, board, atomicInteger);
        lastLikedMessage = new Message("", null);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("Consumer waiting for messages...");

            try {
                Message message = board.consume(lastLikedMessage);

                message.addLike();
                this.lastLikedMessage = message;

                System.out.println("Like set to message content: " + message.getMessageContent()
                        + " Likes count:" + message.getLikesCount() + ""
                        + " Author name: " + message.getAuthorsName());
            } catch (InterruptedException e) {
                //e.printStackTrace();
                break;
            }
        }
    }
}
