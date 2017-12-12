package kt02_probe;

import java.util.concurrent.atomic.AtomicInteger;


class ProductLineForBookContent extends ProductLineForBookCovers {
    ProductLineForBookContent(AtomicInteger firstBookAmount, AtomicInteger secondBookAmount, AtomicInteger thirdBookAmount, BookPartsStorage bookPartsStorage) {
        super(firstBookAmount, secondBookAmount, thirdBookAmount, bookPartsStorage);
    }

    @Override
    public BookPart createBookPart(String bookName, double bookPrice) {
        return new BookContent(bookName, "ee");
    }
}
