package task12_consumer_producer;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

class Message {
    private final String messageContent;
    private final Person author;
    private AtomicInteger likesCount;

    Message(String messageContent, Person author) {
        this.messageContent = messageContent;
        this.author = author;
        this.likesCount = new AtomicInteger(0);
        LocalDateTime localDateTimeCreated = LocalDateTime.now();
    }

    String getMessageContent() {
        return messageContent;
    }

    private int getLikesCount() {
        return likesCount.get();
    }

    synchronized void addLike() {
        likesCount.incrementAndGet();
        System.out.println("Liked " + messageContent + " | by: " + getAuthorsName() + " | Total count: " + getLikesCount());
    }

    String getAuthorsName() {
        return author.getName();
    }
}
