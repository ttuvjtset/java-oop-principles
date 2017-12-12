package kt02_probe;


public class Book {
    private BookContent bookContent;
    private BookCover bookCover;

    public BookContent getBookContent() {
        return bookContent;
    }

    public void setBookContent(BookContent bookContent) {
        this.bookContent = bookContent;
    }

    public BookCover getBookCover() {
        return bookCover;
    }

    public void setBookCover(BookCover bookCover) {
        this.bookCover = bookCover;
    }
}
