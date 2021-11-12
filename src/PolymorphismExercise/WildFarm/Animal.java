package PolymorphismExercise.WildFarm;

public abstract class Animal {

    private String animalName;
    private String animalType;
    private double weight;
    private int footEaten;

    public Animal(String animalName, String animalType, double weight) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.weight = weight;
        this.footEaten = 0;
    }

    public abstract void makeSound();

    public void eat(Food food) {
        this.footEaten += food.getQuantity();
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalType() {
        return animalType;
    }

    public double getWeight() {
        return weight;
    }

    public int getFootEaten() {
        return footEaten;
    }
}
