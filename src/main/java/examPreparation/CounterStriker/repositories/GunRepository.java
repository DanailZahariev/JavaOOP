package examPreparation.CounterStriker.repositories;

import examPreparation.CounterStriker.models.guns.Gun;
import examPreparation.CounterStriker.common.ExceptionMessages;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class GunRepository implements Repository<Gun> {

    private Map<String, Gun> models;

    public GunRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return this.models.values();
    }

    @Override
    public void add(Gun model) {
        if (model == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN_REPOSITORY);
        }
        models.put(model.getName(), model);
    }

    @Override
    public boolean remove(Gun model) {
        Gun gunToRemove = models.remove(model.getName());
        return gunToRemove != null;
    }

    @Override
    public Gun findByName(String name) {
        return models.get(name);
    }
}
