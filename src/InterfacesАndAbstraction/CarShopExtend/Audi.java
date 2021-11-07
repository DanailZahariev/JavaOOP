package interfacesАndAbstraction.CarShopExtend;

public class Audi extends CarImpl implements Rentable {

    private final Integer minRentDay;
    private final Double pricePerDay;

    public Audi(String model, String color, Integer horsePower, String countryProduced, Integer minRentDay, Double pricePerDay) {
        super(model, color, horsePower, countryProduced);
        this.minRentDay = minRentDay;
        this.pricePerDay = pricePerDay;
    }

    public Integer getMinRentDay() {
        return minRentDay;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() +
                "Minimum rental period of " + minRentDay + " days. Price per day  " + pricePerDay;
    }
}
