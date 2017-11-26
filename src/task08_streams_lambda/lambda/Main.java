package task08_streams_lambda.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Student student1 = new Student("Nuko", 5);
        Student student2 = new Student("Millo", 3);
        Student student3 = new Student("Test", 4);

        List<Student> students = Arrays.asList(student1, student2, student3);


        List<Student> filtStud = students
                .stream()
                .filter(s -> s.getName().contains("e") || s.getName().contains("i"))
                .peek(System.out::println)
             //   .filter(s -> s.getHinne() > 3)
                //.forEach(s -> System.out.println(s));
                .collect(Collectors.toList());

        students.stream()
                .filter(s -> s.getName().contains("e") || s.getName().contains("i"))
                //.peek(System.out::println)
                //   .filter(s -> s.getHinne() > 3)
                .forEach(s -> System.out.println(s));

        System.out.println(filtStud);


        List<Student> filtStud2 = students
                .stream()
                .filter(s -> s.getHinne() > 3)
                .collect(Collectors.toList());

        System.out.println(filtStud2);
    }
}
