package p01StudentsByGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        List<Student> students = new LinkedList<>();

        while (!"END".equals(input)){
            String[] inputTokens = input.split("\\s+");
            String firstName = inputTokens[0];
            String lastName = inputTokens[1];
            int group = Integer.parseInt(inputTokens[2]);
            Student student = new Student(firstName, lastName, group);
            students.add(student);
            input = reader.readLine();
        }

        students.stream()
                .filter(s -> s.getGroup() == 2)
                .sorted(Comparator.comparing(Student::getFirstName))
                .forEach(s -> System.out.printf("%s %s\n", s.getFirstName(), s.getLastName()));
    }
}

 class Student {
    private String firstName;
    private String lastName;
    private int group;

    Student(String firstName, String lastName, int group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
    }

    String getFirstName() {
        return this.firstName;
    }

    String getLastName() {
        return this.lastName;
    }

    int getGroup() {
        return this.group;
    }
}


