package task13;

public class PersonConsumer extends Person {
    PersonConsumer(String name, Board board) {
        super(name, board);
    }

    @Override
    public void run() {
        System.out.println("Consumer waiting for messages...");

        if (board.getLastMessage().isPresent()) {
            try {
                board.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            board.getLastMessage().get().addLike();
        }
    }
}
