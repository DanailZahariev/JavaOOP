package polymorphismExercise.Vehicles;

public class Truck extends VehicleImpl {

    private static final double ADDITIONAL_CONSUMPTION = 1.6;
    private static final double REFILL_PERCENTAGE = 0.95;


    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + ADDITIONAL_CONSUMPTION);
    }

    @Override
    public void refuel(double liters) {
        super.refuel(liters * REFILL_PERCENTAGE);
    }
}
