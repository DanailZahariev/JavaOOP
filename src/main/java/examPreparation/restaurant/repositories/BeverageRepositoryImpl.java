package examPreparation.restaurant.repositories;

import examPreparation.restaurant.repositories.interfaces.BeverageRepository;
import examPreparation.restaurant.entities.drinks.interfaces.Beverages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BeverageRepositoryImpl implements BeverageRepository<Beverages> {

    private List<Beverages> beverages;

    public BeverageRepositoryImpl() {
        this.beverages = new ArrayList<>();
    }

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        return beverages.stream().filter(b -> b.getName().equals(drinkName) && b.getBrand().equals(drinkBrand)).findFirst().orElse(null);
    }

    @Override
    public Collection<Beverages> getAllEntities() {
        return Collections.unmodifiableList(beverages);
    }

    @Override
    public void add(Beverages entity) {
        this.beverages.add(entity);
    }
}
