package _martin.socnetwork;

public class Person implements Runnable {

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        //do some stuff
    }
}
