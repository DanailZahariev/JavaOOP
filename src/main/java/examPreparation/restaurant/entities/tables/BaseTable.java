package examPreparation.restaurant.entities.tables;

import examPreparation.restaurant.entities.healthyFoods.interfaces.HealthyFood;
import examPreparation.restaurant.entities.tables.interfaces.Table;
import examPreparation.restaurant.entities.drinks.interfaces.Beverages;

import java.util.ArrayList;
import java.util.Collection;

import static examPreparation.restaurant.common.ExceptionMessages.*;

public abstract class BaseTable implements Table {

    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    protected BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.isReservedTable = false;
    }

    private void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople < 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getTableNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int numberOfPeople() {
        return numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return isReservedTable;
    }

    @Override
    public double allPeople() {
        return allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.isReservedTable = true;
        this.setNumberOfPeople(numberOfPeople);
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        double sumFood = this.healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();
        double sumBeverage = this.beverages.stream().mapToDouble(Beverages::getPrice).sum();
        this.allPeople = this.numberOfPeople * this.pricePerPerson + sumFood + sumBeverage;
        return this.allPeople;
    }

    @Override
    public void clear() {
        this.healthyFood.clear();
        this.beverages.clear();
        this.isReservedTable = false;
        this.numberOfPeople = 0;
        this.allPeople = 0;
    }

    @Override
    public String tableInformation() {
        StringBuilder builder = new StringBuilder();
        builder.append("Table - ").append(this.number)
                .append(System.lineSeparator());
        builder.append("Size - ").append(this.size)
                .append(System.lineSeparator());
        builder.append("Type - ").append(this.getClass().getSimpleName())
                .append(System.lineSeparator());
        builder.append("All price - ").append(String.format("%.2f",this.pricePerPerson));
        return builder.toString().trim();
    }
}
