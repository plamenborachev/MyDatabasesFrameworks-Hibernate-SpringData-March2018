package p05BorderControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        List<Identifiable> citizensRobots = new LinkedList<>();

        while (!"End".equalsIgnoreCase(input)){
            String[] tokens = input.split("\\s+");
            Identifiable identifiable = null;
            if (tokens.length == 3){
                String name = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                String id = tokens[2];
                identifiable = new Citizen(name, age, id);
                citizensRobots.add(identifiable);
            } else if(tokens.length == 2){
                String model = tokens[0];
                String id = tokens[1];
                identifiable = new Robot(model, id);
                citizensRobots.add(identifiable);
            }
            input = reader.readLine();
        }

        String theLastDigitsOfFakeIds = reader.readLine();

        for (Identifiable identifiable : citizensRobots) {
            if (identifiable.getId().endsWith(theLastDigitsOfFakeIds)){
                System.out.println(identifiable.getId());
            }
        }
    }
}
