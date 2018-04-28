package p09StudentsEnrolledIn2014Or2015;

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
            int facultyNumber = Integer.parseInt(inputTokens[0]);
            List<Integer> marks = Arrays.stream(inputTokens)
                    .skip(1L)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());

            Student student = new Student(facultyNumber, marks);
            students.add(student);

            input = reader.readLine();
        }

        students.stream()
                .filter(s -> s.getFacultyNumber() % 100 == 14 || s.getFacultyNumber() % 100 == 15)
                .forEach(s -> System.out.println(s.getMarks().toString().replaceAll("[\\[\\],]", "")));
    }
}

class Student {
    private int facultyNumber;
    private List<Integer> marks;

    Student(int facultyNumber, List<Integer> marks) {
        this.facultyNumber = facultyNumber;
        this.marks = marks;
    }

    int getFacultyNumber() {
        return this.facultyNumber;
    }

    List<Integer> getMarks() {
        return this.marks;
    }
}


