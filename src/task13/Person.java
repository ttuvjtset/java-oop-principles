package task13;

import java.util.concurrent.atomic.AtomicInteger;

abstract class Person implements Runnable {
    AtomicInteger atomicInteger;
    Board board;

    private String name;

    Person(String name, Board board, AtomicInteger atomicInteger) {
        this.name = name;
        this.board = board;
        this.atomicInteger = atomicInteger;
    }

    String getName() {
        return name;
    }

}
