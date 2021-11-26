package examPreparation.spaceStation.models.astronauts;

import examPreparation.spaceStation.models.bags.Bag;

public interface Astronaut {
    String getName();

    double getOxygen();

    boolean canBreath();

    Bag getBag();

    void breath();
}
