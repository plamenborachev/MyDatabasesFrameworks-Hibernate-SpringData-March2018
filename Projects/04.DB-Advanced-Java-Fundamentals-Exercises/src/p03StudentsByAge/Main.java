package p03StudentsByAge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        List<Student> students = new LinkedList<>();

        while (!"END".equals(input)){
            String[] inputTokens = input.split("\\s+");
            String firstName = inputTokens[0];
            String lastName = inputTokens[1];
            int age = Integer.parseInt(inputTokens[2]);

            Student student = new Student(firstName, lastName, age);
            students.add(student);

            input = reader.readLine();
        }

        students.stream()
                .filter(s -> s.getAge() >= 18 && s.getAge() <=24)
                .forEach(s -> System.out.println(String.format("%s %s %d",
                        s.getFirstName(), s.getLastName(), s.getAge())));
    }
}

class Student {
    private String firstName;
    private String lastName;
    private int age;

    Student(String firstName, String lastName, int age) {
        this(firstName, lastName);
        this.age = age;
    }

    Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    String getFirstName() {
        return this.firstName;
    }

    String getLastName() {
        return this.lastName;
    }

    int getAge() {
        return this.age;
    }
}



