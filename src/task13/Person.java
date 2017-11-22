package task13;

abstract class Person implements Runnable {
    Board board;
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    private String name;

    Person(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    String getName() {
        return name;
    }

}
