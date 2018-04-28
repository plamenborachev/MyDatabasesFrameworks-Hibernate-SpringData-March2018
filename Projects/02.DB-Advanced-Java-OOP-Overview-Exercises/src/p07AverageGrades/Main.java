package p07AverageGrades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numberOfStudents = Integer.parseInt(reader.readLine());
        List<Student> students = new ArrayList<>();
        Student student = null;

        for (int i = 0; i < numberOfStudents; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String name = tokens[0];
            List<Double> grades = new ArrayList<>();
            for (int j = 1; j < tokens.length; j++) {
                grades.add(Double.parseDouble(tokens[j]));
            }
            student = new Student(name, grades);
            students.add(student);
        }

        students.stream()
                .filter(s -> s.getAverageGrade() >= 5)
                .sorted(Comparator.comparing(Student::getName).thenComparing((s1, s2) -> Double.compare(s2.getAverageGrade(), s1.getAverageGrade())))
                .forEach(s -> System.out.println(String.format("%s -> %.2f", s.getName(), s.getAverageGrade())));
    }
}
