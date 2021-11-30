package examPreparation.restaurant.repositories;

import examPreparation.restaurant.entities.healthyFoods.interfaces.HealthyFood;
import examPreparation.restaurant.repositories.interfaces.HealthFoodRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HealthFoodRepositoryImpl implements HealthFoodRepository<HealthyFood> {

    private List<HealthyFood> foods;

    public HealthFoodRepositoryImpl() {
        this.foods = new ArrayList<>();
    }

    @Override
    public HealthyFood foodByName(String name) {
        return foods.stream().filter(food -> food.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<HealthyFood> getAllEntities() {
        return Collections.unmodifiableList(foods);
    }

    @Override
    public void add(HealthyFood entity) {
        this.foods.add(entity);
    }
}
