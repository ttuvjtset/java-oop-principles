package kt02_probe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class BookPartsStorage {

    private final List<BookPart> bookParts = new ArrayList<>();

    List<BookPart> getBookParts() {
        return bookParts;
    }

    void addBookPart(BookPart bookPart) {
        synchronized (bookParts) {
            bookParts.add(bookPart);
            bookParts.notifyAll();
        }
    }

    Book popBookIfExists() {
        Book book = new Book();
        synchronized (bookParts) {
            Optional<BookPart> bookCoverInContainer = Optional.empty();
            while (!bookCoverInContainer.isPresent()) {

                bookCoverInContainer = bookParts.stream().filter(s -> s instanceof BookCover).findFirst();
                if (!bookCoverInContainer.isPresent()) {
                    try {
                        bookParts.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Shutting down sleeping client thread");
                        break;
                    }
                }
            }
            bookParts.remove(bookCoverInContainer.get());

            String bookNameOnCover = bookCoverInContainer.get().getBookName();


            //content

            Optional<BookPart> bookContentInContainer = Optional.empty();
            while (!bookContentInContainer.isPresent()) {

                bookContentInContainer = bookParts.stream()
                        .filter(s -> s instanceof BookContent)
                        .filter(s -> s.getBookName().equals(bookNameOnCover))
                        .findFirst();

                if (!bookContentInContainer.isPresent()) {
                    try {
                        bookParts.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Shutting down sleeping client thread");
                        break;
                    }
                }
            }

            bookParts.remove(bookContentInContainer.get());



            List<BookPart> parts = Arrays.asList(bookContentInContainer.get(), bookCoverInContainer.get());

            //parts.stream().filter(lambda).forEach(s-> System.out.println(s));
            book.setBookContent((BookContent) bookContentInContainer.get());
            book.setBookCover((BookCover) bookCoverInContainer.get());

        }
        return book;
    }
}
