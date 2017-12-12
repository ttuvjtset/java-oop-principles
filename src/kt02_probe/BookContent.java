package kt02_probe;

public class BookContent implements BookPart {
    private String bookName;
    private String bookContent;

    BookContent(String bookName, String bookContent) {
        this.bookName = bookName;
        this.bookContent = bookContent;
    }


    public String getBookName() {
        return bookName;
    }

    public String getBookContent() {
        return bookContent;
    }
}
