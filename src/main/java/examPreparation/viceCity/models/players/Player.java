package examPreparation.viceCity.models.players;

import examPreparation.viceCity.repositories.interfaces.Repository;
import examPreparation.viceCity.models.guns.Gun;

public interface Player {
    String getName();

    int getLifePoints();

    boolean isAlive();

    Repository<Gun> getGunRepository();

    void takeLifePoints(int points);
}
