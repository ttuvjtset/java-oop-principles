package kodutoo08;


public class Main {
    public static void main(String[] args) {
        WordPacker wordPacker = new WordPacker();

        Send send = new SendMessage(wordPacker);

        send.addMessage("calvin");
        send.addMessage("bb");
        send.sendAndWriteToFile();

    }
}
