package p09Animals;

import p09Animals.interfaces.SoundProducible;
import p09Animals.models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String animalType = reader.readLine();
        List<SoundProducible> animals = new LinkedList<>();

        while (!"Beast!".equals(animalType)) {
            String[] animalTokens = reader.readLine().split("\\s+");
            String name = animalTokens[0];
            String age = animalTokens[1];
            String gender = animalTokens[2];
            SoundProducible animal = null;
            try {
                switch (animalType) {
                    case "Dog":
                        animal = new Dog(name, age, gender);
                        break;
                    case "Cat":
                        animal = new Cat(name, age, gender);
                        break;
                    case "Frog":
                        animal = new Frog(name, age, gender);
                        break;
                    case "Kitten":
                        animal = new Kitten(name, age, gender);
                        break;
                    case "Tomcat":
                        animal = new Tomcat(name, age, gender);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid input!");
                }
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
            if (animal != null){
                animals.add(animal);
            }
            animalType = reader.readLine();
        }

        for (SoundProducible animal : animals) {
            System.out.println(animal);
        }
    }
}
