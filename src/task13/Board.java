package task13;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Board {
    private Object lock = new Object();
    private List<Message> messages = new ArrayList<>();

    void addMessage(Message message) {
        synchronized (lock) {
            messages.add(message);
            lock.notifyAll();
        }
    }

    Optional<Message> getLastMessage() {
        if (messages.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(messages.get(messages.size() - 1));
        }
    }

    int getMessagesCount() {
        return messages.size();
    }

    Message consume() throws InterruptedException {
        synchronized (lock) {
            while (messages.isEmpty()) {
                lock.wait();
            }
            return getLastMessage();
            //message - id, id on suurem -> tagastatakse
        }
    }
}
