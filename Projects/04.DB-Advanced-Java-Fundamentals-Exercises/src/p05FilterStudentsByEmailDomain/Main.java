package p05FilterStudentsByEmailDomain;

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
            String email = inputTokens[2];

            Student student = new Student(firstName, lastName, email);
            students.add(student);

            input = reader.readLine();
        }

        students.stream()
                .filter(s -> "@gmail.com".equals(s.getEmail().substring(s.getEmail().indexOf("@"))))
                .forEach(s -> System.out.println(String.format("%s %s",
                        s.getFirstName(), s.getLastName())));
    }
}

class Student {
    private String firstName;
    private String lastName;
    private String email;

    Student(String firstName, String lastName, String email) {
        this(firstName, lastName);
        this.email = email;
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

    String getEmail() {
        return this.email;
    }
}


