package _martin.socnetwork;

import java.time.LocalDateTime;

public class LikerPerson extends Person {

    private Board board;
    private LocalDateTime lastLikeTime;

    public LikerPerson(String name, Board board) {
        super(name);
        this.board = board;
        this.lastLikeTime = LocalDateTime.now();
    }

    @Override
    public void run() {
        try {
            likePost();
        } catch (InterruptedException e) {
            System.out.println("Liker halting");
        }
    }

    private void likePost() throws InterruptedException {
        while (!Thread.interrupted()) {
            Message m = board.getMessageAfterTime(lastLikeTime);
            lastLikeTime = LocalDateTime.now();
            m.like();
            System.out.println(this.getName() + " liking post");
        }
    }
}
