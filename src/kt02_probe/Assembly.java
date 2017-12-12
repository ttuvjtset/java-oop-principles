package kt02_probe;

import java.util.concurrent.Callable;


public class Assembly implements Callable<Integer> {
    private int counter;
    private BookPartsStorage bookPartsStorage;

    Assembly(BookPartsStorage bookPartsStorage) {
        this.bookPartsStorage = bookPartsStorage;
    }

    @Override
    public Integer call() throws Exception {
        while (!Thread.interrupted()) {

            Book book = bookPartsStorage.popBookIfExists();
            System.out.println(book.getBookContent() + " " + book.getBookCover());
            counter++;
        }
        return counter;
    }
}
