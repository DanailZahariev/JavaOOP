package polymorphismExercise.VehiclesExtension;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");

        Vehicle car = getVehicle(tokens);

        tokens = scanner.nextLine().split("\\s+");
        Vehicle truck = getVehicle(tokens);

        tokens = scanner.nextLine().split("\\s+");

        Vehicle bus = getVehicle(tokens);

        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();
        vehicleMap.put("Car", car);
        vehicleMap.put("Truck", truck);
        vehicleMap.put("Bus", bus);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            tokens = scanner.nextLine().split("\\s+");
            String commandName = tokens[0];
            String vehicleType = tokens[1];
            Vehicle vehicle = vehicleMap.get(vehicleType);
            try {
                switch (commandName) {
                    case "Drive":
                        double distance = Double.parseDouble(tokens[2]);
                        if (vehicle instanceof Bus) {
                            ((Bus) vehicle).setEmpty(false);
                        }
                        System.out.println(vehicle.drive(distance));
                        break;
                    case "Refuel":
                        double liters = Double.parseDouble(tokens[2]);
                        vehicleMap.get(vehicleType).refuel(liters);
                        break;
                    case "DriveEmpty":
                        double driveEmpty = Double.parseDouble(tokens[2]);
                        if (vehicle instanceof Bus) {
                            ((Bus) vehicle).setEmpty(true);
                        }
                        System.out.println(vehicle.drive(driveEmpty));
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        vehicleMap.values().forEach(System.out::println);
    }

    private static Vehicle getVehicle(String[] tokens) {
        String vehicleType = tokens[0];
        double fuel = Double.parseDouble(tokens[1]);
        double consumption = Double.parseDouble(tokens[2]);
        double tankCapacity = Double.parseDouble(tokens[3]);
        Vehicle vehicle = null;
        switch (vehicleType) {
            case "Car":
                vehicle = new Car(fuel, consumption, tankCapacity);
                break;
            case "Truck":
                vehicle = new Truck(fuel, consumption, tankCapacity);
                break;
            case "Bus":
                vehicle = new Bus(fuel, consumption, tankCapacity);
                break;
        }
        return vehicle;
    }
}
