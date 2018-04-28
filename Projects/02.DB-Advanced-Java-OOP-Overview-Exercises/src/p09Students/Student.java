package p09Students;

public class Student {
    private String name;
    private static int studentsCounter = 0;

    public Student(String name) {
        this.name = name;
        studentsCounter++;
    }

    public static int getStudentsCounter() {
        return studentsCounter;
    }
}
