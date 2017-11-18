package kt08_mocking.tests;

import kt08_mocking.SendMessage;
import kt08_mocking.WordPacker;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SendMessageTest {
    private WordPacker wordPacker;
    private SendMessage sendMessage;

    @Before
    public void setUp() throws Exception {
        wordPacker = mock(WordPacker.class);
        sendMessage = new SendMessage(wordPacker);
    }

    @Test
    public void checkIfMessageWasPackedBeforeSending() throws Exception {
        sendMessage.addMessage("aac");

        sendMessage.sendAndWriteToFile();
        verify(wordPacker).pack("aac");
    }

    @Test
    public void checkIfTwoMessagesWerePackedBeforeSending() throws Exception {
        sendMessage.addMessage("aac");
        sendMessage.addMessage("aab");

        sendMessage.sendAndWriteToFile();
        verify(wordPacker).pack("aac;aab");
    }

    @Test
    public void checkIfThreeMessagesWerePackedBeforeSending() throws Exception {
        sendMessage.addMessage("aac");
        sendMessage.addMessage("aab");
        sendMessage.addMessage("aaa");

        sendMessage.sendAndWriteToFile();
        verify(wordPacker).pack("aac;aab;aaa");
    }

    @Test
    public void checkIfFourMessagesWerePackedBeforeSending() throws Exception {
        sendMessage.addMessage("aac");
        sendMessage.addMessage("aab");
        sendMessage.addMessage("aaa");
        sendMessage.addMessage("aad");

        sendMessage.sendAndWriteToFile();
        verify(wordPacker).pack("aac;aab;aaa;aad");
    }

    @Test
    public void checkIfExampleMessagesWerePackedBeforeSending() throws Exception {
        sendMessage.addMessage("Nägin ahvi!");
        sendMessage.addMessage("Moskiito hammustas kolleegi!");

        sendMessage.sendAndWriteToFile();
        verify(wordPacker).pack("Nägin ahvi!;Moskiito hammustas kolleegi!");
    }

}