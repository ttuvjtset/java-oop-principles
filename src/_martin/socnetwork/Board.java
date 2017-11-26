package _martin.socnetwork;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {

    private List<Message> messages = new ArrayList<>();

    public int getMessageCount() {
        return messages.size();
    }

    public void addMessage(Message message) {
        synchronized (messages) {
            messages.add(message);
            messages.notifyAll(); // inform waiting threads
        }
    }

    public Message getLastMessage() {
        return messages.get(messages.size() - 1);
    }

    public Message getMessageAfterTime(LocalDateTime lastLikeTime) throws InterruptedException {
        synchronized (messages) {
            Optional<Message> message = Optional.empty();
            while (!message.isPresent()) {
                message = messages.stream()
                        .filter(m -> m.getPostingTime().isAfter(lastLikeTime))
                        .findFirst();
                if (!message.isPresent()) {
                    messages.wait();
                }
            }
            return message.get();
        }
    }
}







