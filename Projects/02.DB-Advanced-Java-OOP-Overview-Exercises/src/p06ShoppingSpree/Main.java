package p06ShoppingSpree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Person> persons = new LinkedHashMap<>();
        Map<String, Product> products = new LinkedHashMap<>();

        String[] people = reader.readLine().split(";");
        for (int i = 0; i < people.length; i++) {
            String[] peopleTokens = people[i].split("=");
            String name = peopleTokens[0];
            Double money = Double.parseDouble(peopleTokens[1]);

            try {
                Person person = new Person(name, money);
                persons.putIfAbsent(name, person);
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                return;
            }
        }

        String[] productsInput = reader.readLine().split(";");
        for (int i = 0; i < productsInput.length; i++) {
            String[] productsTokens = productsInput[i].split("=");
            String productName = productsTokens[0];
            double cost = Double.parseDouble(productsTokens[1]);

            try {
                Product product = new Product(productName, cost);
                products.putIfAbsent(productName, product);
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
                return;
            }
        }

        String[] command = reader.readLine().split("\\s+");

        while (!"END".equalsIgnoreCase(command[0])) {
            String personName = command[0];
            String productName = command[1];

            double cost = products.get(productName).getCost();
            double money = persons.get(personName).getMoney();

            try {
                persons.get(personName).addProducts(products.get(productName));
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
            command = reader.readLine().split("\\s+");
        }

        for (Map.Entry<String, Person> person : persons.entrySet()) {
            System.out.print(person.getKey() + " - ");
            if (person.getValue().getProducts().isEmpty()) {
                System.out.println("Nothing bought");
            } else {
                List<String> productsByPerson = new ArrayList<>();
                for (Product product : person.getValue().getProducts()) {
                    productsByPerson.add(product.getName());
                }
                System.out.println(productsByPerson.toString().replaceAll("[\\[\\]]", ""));
            }
        }
    }
}
