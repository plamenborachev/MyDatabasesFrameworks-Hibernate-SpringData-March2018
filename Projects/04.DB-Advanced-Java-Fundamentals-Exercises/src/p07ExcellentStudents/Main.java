package p07ExcellentStudents;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        List<Student> students = new LinkedList<>();

        while (!"END".equals(input)){
            String[] inputTokens = input.split("\\s+");
            String firstName = inputTokens[0];
            String lastName = inputTokens[1];
            List<Integer> marks = Arrays.stream(inputTokens)
                    .skip(2L)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());

            Student student = new Student(firstName, lastName, marks);
            students.add(student);

            input = reader.readLine();
        }

        students.stream()
                .filter(s -> s.getMarks().contains(6))
                .forEach(s -> System.out.println(String.format("%s %s",
                        s.getFirstName(), s.getLastName())));
    }
}

class Student {
    private String firstName;
    private String lastName;
    private List<Integer> marks;

    Student(String firstName, String lastName, List<Integer> marks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.marks = marks;
    }

    String getFirstName() {
        return this.firstName;
    }

    String getLastName() {
        return this.lastName;
    }

    List<Integer> getMarks() {
        return this.marks;
    }
}


