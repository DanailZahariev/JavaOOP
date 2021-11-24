package workingWithAbstractionExercise.CardsWithPower;

public enum Cards {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

    private final int suitPower;

    Cards(int suitPower) {
        this.suitPower = suitPower;
    }

    public int getValue() {
        return suitPower;
    }
}
