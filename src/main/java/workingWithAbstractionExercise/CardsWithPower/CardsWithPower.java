package workingWithAbstractionExercise.CardsWithPower;

public class CardsWithPower {
    private final CardsRank cardsRank;
    private final Cards cards;

    public CardsWithPower(CardsRank cardsRank, Cards cards) {
        this.cardsRank = cardsRank;
        this.cards = cards;
    }

    public int getPower() {
        return this.cards.getValue() + this.cardsRank.getValue();
    }

    public Cards getCards() {
        return cards;
    }

    public CardsRank getCardsRank() {
        return cardsRank;
    }
}
