package examPreparation.restaurant.core;

import examPreparation.restaurant.core.interfaces.Controller;
import examPreparation.restaurant.entities.drinks.Fresh;
import examPreparation.restaurant.entities.drinks.Smoothie;
import examPreparation.restaurant.entities.healthyFoods.Salad;
import examPreparation.restaurant.entities.healthyFoods.VeganBiscuits;
import examPreparation.restaurant.entities.healthyFoods.interfaces.HealthyFood;
import examPreparation.restaurant.entities.tables.InGarden;
import examPreparation.restaurant.entities.tables.Indoors;
import examPreparation.restaurant.entities.tables.interfaces.Table;
import examPreparation.restaurant.repositories.interfaces.BeverageRepository;
import examPreparation.restaurant.repositories.interfaces.HealthFoodRepository;
import examPreparation.restaurant.repositories.interfaces.TableRepository;
import examPreparation.restaurant.common.enums.BeveragesType;
import examPreparation.restaurant.common.enums.HealthyFoodType;
import examPreparation.restaurant.common.enums.TableType;
import examPreparation.restaurant.entities.drinks.interfaces.Beverages;

import static examPreparation.restaurant.common.ExceptionMessages.*;
import static examPreparation.restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double totalMoney;

    public double getTotalMoney() {
        return totalMoney;
    }

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;

    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood healthyFood;
        HealthyFoodType healthyFoodType = HealthyFoodType.valueOf(type);
        if (healthyFoodType.equals(HealthyFoodType.Salad)) {
            healthyFood = new Salad(name, price);
            return addFoodToRepository(name, healthyFood);
        } else if (healthyFoodType.equals(HealthyFoodType.VeganBiscuits)) {
            healthyFood = new VeganBiscuits(name, price);
            return addFoodToRepository(name, healthyFood);
        }
        return null;
    }

    private String addFoodToRepository(String name, HealthyFood food) {
        if (healthFoodRepository.foodByName(name) == null) {
            healthFoodRepository.add(food);
            return String.format(FOOD_ADDED, name);
        } else {
            throw new IllegalArgumentException(String.format(FOOD_EXIST, name));
        }
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        Beverages beverages;
        BeveragesType beveragesType = BeveragesType.valueOf(type);
        if (beveragesType.equals(BeveragesType.Fresh)) {
            beverages = new Fresh(name, counter, brand);
            return addBeverageToRepository(name, brand, beverages);
        } else if (beveragesType.equals(BeveragesType.Smoothie)) {
            beverages = new Smoothie(name, counter, brand);
            return addBeverageToRepository(name, brand, beverages);
        }
        return null;
    }

    private String addBeverageToRepository(String name, String brand, Beverages beverages) {
        if (beverageRepository.beverageByName(name, brand) == null) {
            beverageRepository.add(beverages);
            return String.format(BEVERAGE_ADDED, beverages.getClass().getSimpleName(), brand);
        }
        throw new IllegalArgumentException(String.format(BEVERAGE_EXIST, name));
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table;
        TableType tableType = TableType.valueOf(type);
        if (tableType.equals(TableType.Indoors)) {
            table = new Indoors(tableNumber, capacity);
            return addTableToRepository(tableNumber, table);
        } else if (tableType.equals(TableType.InGarden)) {
            table = new InGarden(tableNumber, capacity);
            return addTableToRepository(tableNumber, table);
        }
        return null;
    }

    private String addTableToRepository(int tableNumber, Table table) {
        if (tableRepository.byNumber(tableNumber) == null) {
            tableRepository.add(table);
            return String.format(TABLE_ADDED, tableNumber);
        } else {
            throw new IllegalArgumentException(String.format(TABLE_IS_ALREADY_ADDED, tableNumber));
        }
    }

    @Override
    public String reserve(int numberOfPeople) {
        Table tableToReserve = tableRepository.getAllEntities().stream()
                .filter(table -> !table.isReservedTable() && table.getSize() >= numberOfPeople)
                .findFirst().orElse(null);
        if (tableToReserve != null) {
            tableToReserve.reserve(numberOfPeople);
            return String.format(TABLE_RESERVED, tableToReserve.getTableNumber(), numberOfPeople);
        } else {
            throw new IllegalArgumentException(String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople));
        }
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        if (tableRepository.byNumber(tableNumber) == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        } else {
            if (healthFoodRepository.foodByName(healthyFoodName) == null) {
                return String.format(NONE_EXISTENT_FOOD, healthyFoodName);
            } else {
                Table table = tableRepository.byNumber(tableNumber);
                table.orderHealthy(healthFoodRepository.foodByName(healthyFoodName));
                return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
            }
        }
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        if (tableRepository.byNumber(tableNumber) == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        } else {
            if (beverageRepository.beverageByName(name, brand) == null) {
                return String.format(NON_EXISTENT_DRINK, name, brand);
            } else {
                Table table = tableRepository.byNumber(tableNumber);
                table.orderBeverages(beverageRepository.beverageByName(name, brand));
                return String.format(BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
            }
        }
    }

    @Override
    public String closedBill(int tableNumber) {
        double bill = tableRepository.byNumber(tableNumber).bill();
        this.totalMoney += bill;
        tableRepository.byNumber(tableNumber).clear();
        return String.format(BILL, tableNumber, bill);
    }


    @Override
    public String totalMoney() {
        return String.format(TOTAL_MONEY, getTotalMoney());
    }
}
