package kodutoo08;


public class WordPacker {

    public String pack(String message) {
        return message.replaceAll("[AEIOUaeiou]", "");
    }
}
