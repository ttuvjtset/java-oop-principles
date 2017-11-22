package task13;

import java.time.LocalDateTime;

class Message {
    private String messageContent;
    private Person author;
    private int likesCount;
    private LocalDateTime localDateTimeCreated;

    Message(String messageContent, Person author) {
        this.messageContent = messageContent;
        this.author = author;
        this.localDateTimeCreated = LocalDateTime.now();
    }

    String getMessageContent() {
        return messageContent;
    }

    int getLikesCount() {
        return likesCount;
    }

    void addLike() {
        likesCount++;
    }

    String getAuthorsName() {
        return author.getName();
    }
}
