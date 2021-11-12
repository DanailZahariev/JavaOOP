package PolymorphismExercise.Vehicles;

public class Car extends VehicleImpl {

    private static final double ADDITIONAL_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + ADDITIONAL_CONSUMPTION);

    }
}
