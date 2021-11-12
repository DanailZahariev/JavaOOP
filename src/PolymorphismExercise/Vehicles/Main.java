package PolymorphismExercise.Vehicles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");

        double carFuel = Double.parseDouble(tokens[1]);
        double carConsumption = Double.parseDouble(tokens[2]);
        Vehicle car = new Car(carFuel, carConsumption);

        tokens = scanner.nextLine().split("\\s+");

        double truckFuel = Double.parseDouble(tokens[1]);
        double truckConsumption = Double.parseDouble(tokens[2]);
        Vehicle truck = new Truck(truckFuel, truckConsumption);

        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();
        vehicleMap.put("Car", car);
        vehicleMap.put("Truck", truck);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            tokens = scanner.nextLine().split("\\s+");
            String commandName = tokens[0];
            String vehicleType = tokens[1];

            switch (commandName) {
                case "Drive":
                    double distance = Double.parseDouble(tokens[2]);
                    System.out.println(vehicleMap.get(vehicleType).drive(distance));
                    break;
                case "Refuel":
                    double liters = Double.parseDouble(tokens[2]);
                    vehicleMap.get(vehicleType).refuel(liters);
                    break;
            }
        }
        vehicleMap.values().forEach(System.out::println);
    }
}
