package kt02_probe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class Assembly implements Callable<Integer> {
    private int counter;
    private BookPartsStorage bookPartsStorage;
    private List<Book> assembledBooks = new ArrayList<>();

    Assembly(BookPartsStorage bookPartsStorage) {
        this.bookPartsStorage = bookPartsStorage;
    }

    @Override
    public Integer call() throws Exception {
        while (!Thread.interrupted()) {

            BookCover bookCover = (BookCover) bookPartsStorage.popBookCoverIfExists();
            String bookNameOnCover = bookCover.getBookName();
            BookContent bookContent = (BookContent) bookPartsStorage.popBookContentIfExists(s-> s.getBookName().equals(bookNameOnCover));


            System.out.println(bookCover.getBookName() + " " + bookContent.getBookName());
            Book book = new Book();
            book.setBookCover(bookCover);
            book.setBookContent(bookContent);
            counter++;
            assembledBooks.add(book);
            assembledBooks.stream().forEach(s-> System.out.println("xxx" + s.getBookCover().getBookName()));
        }


        return counter;
    }
}
