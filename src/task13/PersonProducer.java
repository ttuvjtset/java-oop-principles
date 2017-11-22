package task13;

public class PersonProducer extends Person {
    PersonProducer(String name, Board board) {
        super(name, board);
    }

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Message message = new Message("hello", this);

        super.board.addMessage(message);
        System.out.println(message.getAuthorsName() + " written.");

    }
}
