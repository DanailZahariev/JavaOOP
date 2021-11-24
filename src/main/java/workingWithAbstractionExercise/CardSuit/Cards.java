package workingWithAbstractionExercise.CardSuit;

public enum Cards {
    CLUBS(0),
    DIAMONDS(1),
    HEARTS(2),
    SPADES(3);

    private int value;
    private String name;

    Cards(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
