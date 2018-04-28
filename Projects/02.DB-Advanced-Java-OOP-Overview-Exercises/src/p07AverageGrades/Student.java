package p07AverageGrades;

import java.util.List;

public class Student {
    private String name;
    private List<Double> grades;

    public Student(String name, List<Double> grades) {
        this.name = name;
        this.grades = grades;
    }

    public String getName() {
        return this.name;
    }

    public double getAverageGrade(){
        return this.grades.stream().mapToDouble(Double::valueOf).average().getAsDouble();
    }
}
