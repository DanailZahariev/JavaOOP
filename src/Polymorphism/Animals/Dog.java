package Polymorphism.Animals;

public class Dog extends Animal {


    public Dog(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    public String explainSelf() {
        return "I am " + getName() + " and my favourite food is " + getFavouriteFood() + System.lineSeparator() + "DJAAF";
    }
}
