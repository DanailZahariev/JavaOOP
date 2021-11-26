package examPreparation.spaceStation.models.planets;

import java.util.ArrayList;
import java.util.List;

import static examPreparation.spaceStation.common.ExceptionMessages.PLANET_NAME_NULL_OR_EMPTY;

public class PlanetImpl implements Planet {

    private String name;
    private List<String> items;

    public PlanetImpl(String name) {
        setName(name);
        this.items = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(PLANET_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public List<String> getItems() {
        return items;
    }

    @Override
    public String getName() {
        return name;
    }
}
