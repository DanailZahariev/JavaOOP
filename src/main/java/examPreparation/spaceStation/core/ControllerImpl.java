package examPreparation.spaceStation.core;

import examPreparation.spaceStation.models.astronauts.Astronaut;
import examPreparation.spaceStation.models.astronauts.Biologist;
import examPreparation.spaceStation.models.astronauts.Geodesist;
import examPreparation.spaceStation.models.astronauts.Meteorologist;
import examPreparation.spaceStation.models.mission.Mission;
import examPreparation.spaceStation.models.mission.MissionImpl;
import examPreparation.spaceStation.models.planets.Planet;
import examPreparation.spaceStation.models.planets.PlanetImpl;
import examPreparation.spaceStation.repositories.AstronautRepository;
import examPreparation.spaceStation.repositories.PlanetRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static examPreparation.spaceStation.common.ConstantMessages.*;
import static examPreparation.spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private int exploredPlanets;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        if (type.equals("Biologist")) {
            astronaut = new Biologist(astronautName);
        } else if (type.equals("Geodesist")) {
            astronaut = new Geodesist(astronautName);
        } else if (type.equals("Meteorologist")) {
            astronaut = new Meteorologist(astronautName);
        } else {
            throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        this.astronautRepository.add(astronaut);
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        planet.getItems().addAll(Arrays.asList(items));
        this.planetRepository.add(planet);
        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        if (this.astronautRepository.getModels().stream().noneMatch(a -> a.getName().equals(astronautName))) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        Astronaut astronaut = this.astronautRepository.findByName(astronautName);
        this.astronautRepository.remove(astronaut);
        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        Planet planet = this.planetRepository.findByName(planetName);
        List<Astronaut> breathableAstronaut = this.astronautRepository.getModels().stream()
                .filter(a -> a.getOxygen() > 60).collect(Collectors.toList());
        if (breathableAstronaut.isEmpty()) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }
        int countBeforeMission = breathableAstronaut.size();
        Mission mission = new MissionImpl();
        mission.explore(planet, breathableAstronaut);
        exploredPlanets++;
        return String.format(PLANET_EXPLORED, planetName, countBeforeMission - breathableAstronaut.size());
    }

    @Override
    public String report() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(REPORT_PLANET_EXPLORED, exploredPlanets)).append(System.lineSeparator());
        builder.append(REPORT_ASTRONAUT_INFO).append(System.lineSeparator());
        this.astronautRepository.getModels().forEach(a -> {
            builder.append(String.format(REPORT_ASTRONAUT_NAME, a.getName())).append(System.lineSeparator());
            builder.append(String.format(REPORT_ASTRONAUT_OXYGEN, a.getOxygen())).append(System.lineSeparator());
            if (a.getBag().getItems().isEmpty()) {
                builder.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, "none")).append(System.lineSeparator());
            } else {
                Collection<String> items = a.getBag().getItems();
                builder.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, String.join(", ", items))).append(System.lineSeparator());
            }
        });
        return builder.toString().trim();
    }
}
