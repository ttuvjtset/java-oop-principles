package task13;

import java.time.LocalDateTime;

class Message {
    int id = 0;
    private String messageContent;
    //private String contentAuthor;
    private Person person;
    private int likesCount;
    private LocalDateTime localDateTimeCreated;

    Message(String messageContent, Person person) {
        this.messageContent = messageContent;
        //this.contentAuthor = contentAuthor;
        this.person = person;
        this.localDateTimeCreated = LocalDateTime.now();
    }

    public String getMessageContent() {
        return messageContent;
    }

    public int getLikesCount() {
        return likesCount;
    }

    void addLike() {
        likesCount++;
    }

    String getAuthorsName() {
        return person.getName();
    }


}
