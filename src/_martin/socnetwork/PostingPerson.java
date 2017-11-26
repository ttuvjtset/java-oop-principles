package _martin.socnetwork;

public class PostingPerson extends Person {

    private Board board;

    public PostingPerson(String name, Board board) {
        super(name);
        this.board = board;
    }

    @Override
    public void run() {
        try {
            doPost();
        } catch (InterruptedException e) {
            System.out.println("Poster halted");
        }
    }

    private void doPost() throws InterruptedException {
        while (!Thread.interrupted()) {
            Thread.sleep(20);
            board.addMessage(new Message(this, "Hei-hei!"));
            System.out.println(this.getName() + " created new post");
        }
    }
}
