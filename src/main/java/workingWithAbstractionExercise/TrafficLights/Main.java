package workingWithAbstractionExercise.TrafficLights;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tokens = scanner.nextLine().split(" ");
        int count = Integer.parseInt(scanner.nextLine());
        List<TrafficLights> trafficLights = new ArrayList<>();
        
        for (String color : tokens) {
            TrafficLights lights = new TrafficLights(Colors.valueOf(color));
            trafficLights.add(lights);
        }
        for (int i = 0; i < count; i++) {
            trafficLights.forEach(light -> {
                light.changeColor();
                System.out.print(light.getColors() + " ");
            });
            System.out.println();
        }
    }
}
