package polymorphismExercise.WildFarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal {

    private String livingRegion;

    public Mammal(String animalName, String animalType, double weight, String livingRegion) {
        super(animalName, animalType, weight);
        this.livingRegion = livingRegion;
    }

    public String getLivingRegion() {
        return livingRegion;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("##.##");
        return String.format("%s[%s, %s, %s, %d]",
                getAnimalType(), this.getAnimalName(),
                format.format(getWeight()), this.livingRegion, getFootEaten());
    }

}
