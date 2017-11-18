package kt08_mocking;


public class SendMessage implements Send {
    private static final String PATH_TO_FILE = "src\\kt08_mocking\\message.txt";

    private String messageToSend = "";

    private WordPacker wordPacker;

    public SendMessage(WordPacker wordPacker) {
        this.wordPacker = wordPacker;
    }

    @Override
    public String addMessage(String message) {

        if (messageToSend.equals("")) {
            messageToSend += message;
        } else {
            messageToSend += ";" + message;
        }

        return messageToSend;
    }

    @Override
    public void sendAndWriteToFile() {

        String packedMessageToSend = wordPacker.pack(messageToSend);

        try {
            WriteToFile writeToFile = new WriteToFile(PATH_TO_FILE);
            writeToFile.writeData(packedMessageToSend);
            writeToFile.close();
        } catch (NullPointerException e) {
            System.out.println("Nothing to write!");
        }

        messageToSend = "";
    }
}
