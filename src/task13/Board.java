package task13;

import java.util.ArrayList;
import java.util.List;

class Board {
    private Object lock = new Object();
    private List<Message> messages = new ArrayList<>();
    int size;

    Message getLastMessage() {
        return messages.get(messages.size() - 1);
    }

    int getMessagesCount() {
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

            while (messages.size() == 0 || getLastMessage().equals(message)) {
                messages.wait();
            }

            size = getMessagesCount();
            return getLastMessage();
            //message - id, id on suurem -> tagastatakse
        }
    }
}
