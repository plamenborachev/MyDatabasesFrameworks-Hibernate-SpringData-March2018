package p08Vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] carTokens = reader.readLine().split("\\s+");
        Vehicle car = new Car(Double.parseDouble(carTokens[1]), Double.parseDouble(carTokens[2]));

        String[] truckTokens = reader.readLine().split("\\s+");
        Vehicle truck = new Truck(Double.parseDouble(truckTokens[1]), Double.parseDouble(truckTokens[2]));

        int numberOfCommands = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numberOfCommands; i++) {
            String[] commandTokens = reader.readLine().split("\\s+");
            if (commandTokens.length == 3){
                switch (commandTokens[0]) {
                    case "Drive":
                        double distance = Double.parseDouble(commandTokens[2]);
                        if ("Car".equals(commandTokens[1])) {
                            System.out.println(car.drive(distance));
                        } else if ("Truck".equals(commandTokens[1])) {
                            System.out.println(truck.drive(distance));
                        }
                        break;
                    case "Refuel":
                        double fuel = Double.parseDouble(commandTokens[2]);
                        if ("Car".equals(commandTokens[1])) {
                            car.refuel(fuel);
                        } else if ("Truck".equals(commandTokens[1])) {
                            truck.refuel(fuel);
                        }
                        break;
                }
            }

        }

        System.out.println(car);
        System.out.println(truck);
    }
}
