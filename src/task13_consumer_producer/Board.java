package task13_consumer_producer;

import java.util.ArrayList;
import java.util.List;

class Board {

    private List<Message> messages = new ArrayList<>();


    private Message getLastMessage() {
        return messages.get(messages.size() - 1);
    }

    private int getMessagesCount() {
        return messages.size();
    }

    void addMessage(Message message) {
        synchronized (messages) {
            messages.add(message);
            messages.notifyAll();
        }
    }

    Message consume(Message message) throws InterruptedException {
        synchronized (messages) {

            while (getMessagesCount() == 0 || getLastMessage().equals(message)) {
                messages.wait();
            }

            return getLastMessage();
        }
    }
}
