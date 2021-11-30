package examPreparation.restaurant;

import examPreparation.restaurant.entities.healthyFoods.interfaces.HealthyFood;
import examPreparation.restaurant.entities.tables.interfaces.Table;
import examPreparation.restaurant.core.ControllerImpl;
import examPreparation.restaurant.core.EngineImpl;
import examPreparation.restaurant.core.interfaces.Controller;
import examPreparation.restaurant.entities.drinks.interfaces.Beverages;

import examPreparation.restaurant.io.ConsoleReader;
import examPreparation.restaurant.io.ConsoleWriter;
import examPreparation.restaurant.repositories.BeverageRepositoryImpl;
import examPreparation.restaurant.repositories.HealthFoodRepositoryImpl;
import examPreparation.restaurant.repositories.TableRepositoryImpl;
import examPreparation.restaurant.repositories.interfaces.BeverageRepository;
import examPreparation.restaurant.repositories.interfaces.HealthFoodRepository;
import examPreparation.restaurant.repositories.interfaces.TableRepository;

public class Main {
    public static void main(String[] args) {

        HealthFoodRepository<HealthyFood> healthFoodRepository = new HealthFoodRepositoryImpl();
        BeverageRepository<Beverages> beverageRepository = new BeverageRepositoryImpl();
        TableRepository<Table> tableRepository = new TableRepositoryImpl();


        Controller controller = new ControllerImpl(healthFoodRepository, beverageRepository, tableRepository);

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();

    }
}
