package p06BirthdayCelebrations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        List<Birthable> citizensAndPets = new ArrayList<>();

        while (!"End".equalsIgnoreCase(input)){
            String[] tokens = input.split("\\s+");
            Birthable birthable = null;
            switch (tokens[0]){
                case "Citizen":
                    String name = tokens[1];
                    int age = Integer.parseInt(tokens[2]);
                    String id = tokens[3];
                    String birthday = tokens[4];
                    birthable = new Citizen(name, age, id, birthday);
                    citizensAndPets.add(birthable);
                    break;
                case "Pet":
                    name = tokens[1];
                    birthday = tokens[2];
                    birthable = new Pet(name, birthday);
                    citizensAndPets.add(birthable);
                    break;
                default:
                    break;
            }
            input = reader.readLine();
        }

        String year = reader.readLine();

        for (Birthable birthable : citizensAndPets) {
            if (birthable.getBirthday().endsWith(year)){
                System.out.println(birthable.getBirthday());
            }
        }
    }
}
