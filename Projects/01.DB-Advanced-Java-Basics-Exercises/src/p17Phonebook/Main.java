package p17Phonebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        Map<String, String> phonebook = new LinkedHashMap<>();

        while (!"END".equals(input[0])){
            if ("A".equals(input[0])){
                String name = input[1];
                String number = input[2];
                phonebook.put(name, number);
            } else if("S".equals(input[0])){
                String name = input[1];
                if (phonebook.containsKey(name)){
                    System.out.println(String.format("%s -> %s", name, phonebook.get(name)));
                } else {
                    System.out.println(String.format("Contact %s does not exist.", name));
                }
            }
            input = reader.readLine().split(" ");
        }
    }
}
