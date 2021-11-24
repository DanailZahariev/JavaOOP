package interfacesAndAbstractionExercise.BirthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        List<Birthable> birthables = new ArrayList<>();


        while (!input.equals("End")) {

            String[] tokens = input.split("\\s+");
            String objectType = tokens[0];
            switch (objectType) {
                case "Citizen":
                    String citizenName = tokens[1];
                    int citizenAge = Integer.parseInt(tokens[2]);
                    String citizenId = tokens[3];
                    String citizenBirthDate = tokens[4];
                    Citizen citizen = new Citizen(citizenName, citizenAge, citizenId, citizenBirthDate);
                    birthables.add(citizen);
                    break;
                case "Pet":
                    String petName = tokens[1];
                    String petBirthDate = tokens[2];
                    Pet pet = new Pet(petName, petBirthDate);
                    birthables.add(pet);
                    break;
            }
            input = scanner.nextLine();
        }
        String birthYear = scanner.nextLine();

        System.out.println(birthables.stream().map(Birthable::getBirthDate)
                .filter(birth -> birth.endsWith(birthYear)).collect(Collectors.joining(System.lineSeparator())));
    }
}
