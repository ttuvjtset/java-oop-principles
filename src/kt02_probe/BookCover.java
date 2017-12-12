package kt02_probe;


class BookCover implements BookPart {
    private String bookName;
    private double bookPrice;

    BookCover(String bookName, double bookPrice) {
        this.bookName = bookName;
        this.bookPrice = bookPrice;
    }


    public String getBookName() {
        return bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }
}
