package p06FilterStudentsByPhone;

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
            String phoneNumber = inputTokens[2];

            Student student = new Student(firstName, lastName, phoneNumber);
            students.add(student);

            input = reader.readLine();
        }

        students.stream()
                .filter(s -> s.getPhoneNumber().startsWith("02") || s.getPhoneNumber().startsWith("+3592"))
                .forEach(s -> System.out.println(String.format("%s %s",
                        s.getFirstName(), s.getLastName())));
    }
}

 class Student {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    Student(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    String getFirstName() {
        return this.firstName;
    }

    String getLastName() {
        return this.lastName;
    }

    String getPhoneNumber() {
        return this.phoneNumber;
    }
}

