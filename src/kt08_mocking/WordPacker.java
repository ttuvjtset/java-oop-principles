package kt08_mocking;


public class WordPacker {

    public String pack(String message) {
        return message.replaceAll("[AEIOUaeiou]", "");
    }
}
