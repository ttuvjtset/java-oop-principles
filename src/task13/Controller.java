package task13;

public class Controller {
    public static void main(String[] args) {
        Board board = new Board();

        startPersonProducer(board, "Name1");
        startPersonProducer(board, "Name2");
        startPersonProducer(board, "Name3");

        startPersonConsumer(board, "NameConsumer1");

    }

    private static void startPersonConsumer(Board board, String name) {
        Person person1 = new PersonConsumer(name, board);
        Thread person1Thread = new Thread(person1);
        person1Thread.start();
    }

    private static void startPersonProducer(Board board, String name) {
        Person person1 = new PersonProducer(name, board);
        Thread person1Thread = new Thread(person1);
        person1Thread.start();
    }
}
