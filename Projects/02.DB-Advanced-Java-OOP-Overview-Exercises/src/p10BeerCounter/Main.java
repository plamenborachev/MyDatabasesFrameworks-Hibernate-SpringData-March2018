package p10BeerCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input =reader.readLine().split("\\s+");

        while (!"End".equalsIgnoreCase(input[0])){

            BeerCounter.buyBeer(Integer.parseInt(input[0]));
            BeerCounter.drinkBeer(Integer.parseInt(input[1]));

            try {
                input =reader.readLine().split("\\s+");
            } catch (Exception e) {
                break;
            }
        }

        System.out.println(BeerCounter.getBeerInStock() + " " + BeerCounter.getBeersDrankCount());
    }
}
