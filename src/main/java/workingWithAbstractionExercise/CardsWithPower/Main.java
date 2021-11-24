package workingWithAbstractionExercise.CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ranks = scanner.nextLine();
        String suits = scanner.nextLine();
        CardsWithPower cardsWithPower = new CardsWithPower(CardsRank.valueOf(ranks),Cards.valueOf(suits));
        System.out.printf("Card name: %s of %s; Card power: %d", cardsWithPower.getCardsRank(),
                cardsWithPower.getCards(), cardsWithPower.getPower());
    }
}
