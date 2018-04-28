package p10GroupByGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        List<Person> students = new LinkedList<>();

        while (!"END".equals(input)){
            String[] inputTokens = input.split("\\s+");
            String name = inputTokens[0] + " " + inputTokens[1];
            int group = Integer.parseInt(inputTokens[2]);
            Person student = new Person(name, group);
            students.add(student);
            input = reader.readLine();
        }

        students.stream()
                .collect(Collectors.groupingBy(Person::getGroup))
                .forEach((key, value) -> System.out.println(String.format("%s - %s",
                        key, value.toString().replaceAll("[\\]\\[]", ""))));
    }
}
