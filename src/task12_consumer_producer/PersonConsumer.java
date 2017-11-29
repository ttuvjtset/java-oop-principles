package task12_consumer_producer;

import java.util.concurrent.atomic.AtomicInteger;

class PersonConsumer extends Person {
    private Message lastLikedMessage;

    PersonConsumer(String name, Board board, AtomicInteger atomicInteger) {
        super(name, board, atomicInteger);
        lastLikedMessage = new Message("", null);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("OrderProcessor waiting for messages...");

            try {
                //Thread.sleep(30);
                Message message = board.consume(lastLikedMessage);

                message.addLike();
                this.lastLikedMessage = message;

//                System.out.println("Liked message with content: " + message.getMessageContent()
//                        + " | Likes count:" + message.getLikesCount() + ""
//                        + " | Author name: " + message.getAuthorsName());
            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println("OrderProcessor thread interrupted!");
                break;
            }
        }
    }
}
