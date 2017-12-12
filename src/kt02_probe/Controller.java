package kt02_probe;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;


public class Controller {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AtomicInteger firstBookAmount = new AtomicInteger(0);
        AtomicInteger secondBookAmount = new AtomicInteger(0);
        AtomicInteger thirdBookAmount = new AtomicInteger(0);

        BookPartsStorage bookPartsStorage = new BookPartsStorage();

        ExecutorService executor = Executors.newFixedThreadPool(8);

        Future<Integer> productLine = executor.submit(new ProductLineForBookCovers(firstBookAmount, secondBookAmount, thirdBookAmount, bookPartsStorage));
        Future<Integer> productLine1 = executor.submit(new ProductLineForBookCovers(firstBookAmount, secondBookAmount, thirdBookAmount, bookPartsStorage));
        Future<Integer> productLine2 = executor.submit(new ProductLineForBookCovers(firstBookAmount, secondBookAmount, thirdBookAmount, bookPartsStorage));

        AtomicInteger firstBookAmount2 = new AtomicInteger(0);
        AtomicInteger secondBookAmount3 = new AtomicInteger(0);
        AtomicInteger thirdBookAmount4 = new AtomicInteger(0);

        Future<Integer> productLine3 = executor.submit(new ProductLineForBookContent(firstBookAmount2, secondBookAmount3, thirdBookAmount4, bookPartsStorage));
        Future<Integer> productLine4 = executor.submit(new ProductLineForBookContent(firstBookAmount2, secondBookAmount3, thirdBookAmount4, bookPartsStorage));

        Future<Integer> assemblyLine = executor.submit(new Assembly(bookPartsStorage));
        Future<Integer> assemblyLine1 = executor.submit(new Assembly(bookPartsStorage));
        Future<Integer> assemblyLine2 = executor.submit(new Assembly(bookPartsStorage));


        System.out.println(productLine.get());
        System.out.println(productLine1.get());
        System.out.println(productLine2.get());

        System.out.println("BookContent" + productLine3.get());
        System.out.println("BookContent" + productLine4.get());
        assemblyLine.get();
        assemblyLine1.get();
        assemblyLine2.get();


        System.out.println("Total count: " + bookPartsStorage.getBookParts().stream()
                .filter(s -> s instanceof BookContent)
                .filter(s -> s.getBookName().equals("Book 1"))
                .count());
        System.out.println("Total count: " + bookPartsStorage.getBookParts().stream().filter(s -> s instanceof BookContent).filter(s -> s.getBookName().equals("Book 2")).count());
        System.out.println("Total count: " + bookPartsStorage.getBookParts().stream().filter(s -> s instanceof BookContent).filter(s -> s.getBookName().equals("Book 3")).count());
        System.out.println("Total count: " + (long) bookPartsStorage.getBookParts().size());

        System.out.println(bookPartsStorage.getBookParts());


        executor.shutdown();

    }
}
