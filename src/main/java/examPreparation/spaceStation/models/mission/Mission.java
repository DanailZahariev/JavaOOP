package examPreparation.spaceStation.models.mission;

import examPreparation.spaceStation.models.astronauts.Astronaut;
import examPreparation.spaceStation.models.planets.Planet;

import java.util.List;

public interface Mission {
    void explore(Planet planet, List<Astronaut> astronauts);
}
