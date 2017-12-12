package kt02_probe;


import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductLineForBookCovers implements Callable<Integer> {
    private AtomicInteger firstBookAmount;
    private AtomicInteger secondBookAmount;
    private AtomicInteger thirdBookAmount;
    private BookPartsStorage bookPartsStorage;
    private int internalCounter;

    ProductLineForBookCovers(AtomicInteger firstBookAmount, AtomicInteger secondBookAmount, AtomicInteger thirdBookAmount, BookPartsStorage bookPartsStorage) {
        this.firstBookAmount = firstBookAmount;
        this.secondBookAmount = secondBookAmount;
        this.thirdBookAmount = thirdBookAmount;
        this.bookPartsStorage = bookPartsStorage;
    }


    @Override
    public Integer call() throws Exception {
        createBookParts(firstBookAmount, "Book 1", 14.99, 7);
        createBookParts(secondBookAmount, "Book 2", 14.99, 5);
        createBookParts(thirdBookAmount, "Book 3", 14.99, 2);

        return internalCounter;
    }

    private void createBookParts(AtomicInteger firstBookAmount, String bookName, double bookPrice, int booksNeeded)
            throws InterruptedException {
        while (firstBookAmount.incrementAndGet() <= booksNeeded) {
            bookPartsStorage.addBookPart(createBookPart(bookName, bookPrice));
            internalCounter++;
            //Thread.sleep(1);
        }
    }

    public BookPart createBookPart(String bookName, double bookPrice) {
        return new BookCover(bookName, bookPrice);
    }
}
