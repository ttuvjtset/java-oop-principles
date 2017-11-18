package task08_streams.lambda;

public class Student {
    private String name;

    private int hinne;

    Student(String name, int hinne) {
        this.name = name;
        this.hinne = hinne;
    }

    String getName() {
        return name;
    }

    public int getHinne() {
        return hinne;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", hinne=" + hinne +
                '}';
    }
}
