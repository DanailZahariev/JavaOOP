package workingWithAbstraction.HotelReservation;

public enum Discount {
    NONE(0),
    SECOND_VISIT(0.10),
    VIP(0.20);

    private double value;

    Discount(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static Discount getDiscount(String discount) {
        switch (discount) {
            case "VIP":
                return VIP;
            case "SecondVisit":
                return SECOND_VISIT;
            case "None":
                return NONE;
            default:
                throw new IllegalArgumentException("Unknown discount " + discount);
        }
    }
}
