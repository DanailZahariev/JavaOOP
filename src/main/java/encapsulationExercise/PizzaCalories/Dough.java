package encapsulationExercise.PizzaCalories;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        setWeight(weight);
        setFlourType(flourType);
        setBakingTechnique(bakingTechnique);
    }

    private void setBakingTechnique(String bakingTechnique) {
        switch (bakingTechnique) {
            case "Crispy":
            case "Chewy":
            case "Homemade":
                this.bakingTechnique = bakingTechnique;
                break;
            default:
                throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setFlourType(String flourType) {
        if (flourType.equals("White") || flourType.equals("Wholegrain")) {
            this.flourType = flourType;
        } else {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setWeight(double weight) {
        if (weight >= 1 && weight <= 200) {
            this.weight = weight;
        } else {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
    }

    public double calculateCalories() {
        //For example, white dough has a modifier of 1.5, a chewy dough has a modifier of 1.1,
        // which means that a white chewy dough weighting 100 grams will have (2 * 100) * 1.5 * 1.1 = 330.00 total calories.
        double flourTypeModification = getFlourTypeModification(this.flourType);
        double bakingTechniqueModification = getBakingTechniqueModification(this.bakingTechnique);
        return (2 * this.weight) * flourTypeModification * bakingTechniqueModification;
    }

    private double getBakingTechniqueModification(String bakingTechnique) {
        switch (bakingTechnique) {
            case "Crispy":
                return 0.9;
            case "Chewy":
                return 1.1;
            case "Homemade":
                return 1;
            default:
                return 0;
        }
    }

    private double getFlourTypeModification(String flourType) {
        if (flourType.equals("White")) {
            return 1.5;
        } else if (flourType.equals("Wholegrain")) {
            return 1;
        }
        return 0;
    }
}
