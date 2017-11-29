package task12_consumer_producer;

import java.util.concurrent.atomic.AtomicInteger;

abstract class Person implements Runnable {
    final AtomicInteger atomicInteger;
    final Board board;

    private final String name;

    Person(String name, Board board, AtomicInteger atomicInteger) {
        this.name = name;
        this.board = board;
        this.atomicInteger = atomicInteger;
    }

    String getName() {
        return name;
    }

}
