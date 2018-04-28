package p07Mankind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] studentTokens = reader.readLine().split("\\s+");

        String studentFirstName = studentTokens[0];
        String studentLastName = studentTokens[1];
        String facultyNumber = studentTokens[2];

        String[] workerTokens = reader.readLine().split("\\s+");

        String workerFirstName = workerTokens[0];
        String workerLastName = workerTokens[1];
        double weekSalary = Double.parseDouble(workerTokens[2]);
        double workingHours = Double.parseDouble(workerTokens[3]);

        Human student = null;
        Human worker = null;

        try{
            student = new Student(studentFirstName, studentLastName, facultyNumber);
            worker = new Worker(workerFirstName, workerLastName, weekSalary, workingHours);
            System.out.println(student);
            System.out.println(worker);
        } catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
            return;
        }
    }
}
