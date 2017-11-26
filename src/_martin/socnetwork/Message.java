package _martin.socnetwork;

import java.time.LocalDateTime;

public class Message {
    private Person author;
    private String message;
    private int likesCount;
    private LocalDateTime postingTime;

    public Message(Person author, String message) {
        this.author = author;
        this.message = message;
        this.likesCount = 0;
        this.postingTime = LocalDateTime.now();
    }

    public LocalDateTime getPostingTime() {
        return postingTime;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void like() {
        this.likesCount++;
    }
}
