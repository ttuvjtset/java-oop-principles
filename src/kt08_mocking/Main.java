package kt08_mocking;


public class Main {
    public static void main(String[] args) {
        WordPacker wordPacker = new WordPacker();

        Send send = new SendMessage(wordPacker);

        send.addMessage("calvin");
        send.addMessage("bb");
        send.sendAndWriteToFile();

    }
}
