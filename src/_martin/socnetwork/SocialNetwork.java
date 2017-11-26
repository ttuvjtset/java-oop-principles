package _martin.socnetwork;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SocialNetwork {
    public static void main(String[] args) {
        new SocialNetwork().createNetwork();
    }

    void createNetwork() {
        Board board = new Board();

        ExecutorService e = Executors.newFixedThreadPool(10);

        // loome postitajad
        for (int i = 0; i < 3; i++) {
            Runnable r = new PostingPerson("Mary " + i, board);
            e.execute(r);
        }
        // loome laikijad
        for (int i = 0; i < 7; i++) {
            Runnable r = new LikerPerson("Jane " + i, board);
            e.execute(r);
        }

        try {
            e.awaitTermination(7, TimeUnit.SECONDS);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        //e.shutdown(); // join
        e.shutdownNow(); // interrupt
    }
}
