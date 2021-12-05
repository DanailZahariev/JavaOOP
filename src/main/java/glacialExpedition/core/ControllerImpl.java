package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int exploredStates;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;
        if (type.equals("AnimalExplorer")) {
            explorer = new AnimalExplorer(explorerName);
        } else if (type.equals("GlacierExplorer")) {
            explorer = new GlacierExplorer(explorerName);
        } else if (type.equals("NaturalExplorer")) {
            explorer = new NaturalExplorer(explorerName);
        } else {
            throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }
        explorerRepository.add(explorer);
        return String.format(EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        List<String> exhibit = Arrays.asList(exhibits);
        State state = new StateImpl(stateName);
        state.getExhibits().addAll(exhibit);
        this.stateRepository.add(state);
        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        if (explorer == null){
            throw new IllegalArgumentException(String.format(EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        explorerRepository.remove(explorer);
        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        List<Explorer> explorers = explorerRepository.getCollection().stream().filter(e -> e.getEnergy() > 50).collect(Collectors.toList());
        if (explorers.isEmpty()) {
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        }
        int startExploring = explorers.size();
        Mission mission = new MissionImpl();
        State state = stateRepository.getCollection().stream().filter(s -> s.getName().equals(stateName)).findFirst().orElse(null);
        mission.explore(state, explorers);
        exploredStates++;
        return String.format(STATE_EXPLORER, stateName, startExploring - explorers.size());
    }

    @Override
    public String finalResult() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(FINAL_STATE_EXPLORED, exploredStates)).append(System.lineSeparator());
        builder.append(FINAL_EXPLORER_INFO).append(System.lineSeparator());
        this.explorerRepository.getCollection().forEach(e -> {
            builder.append(String.format(FINAL_EXPLORER_NAME, e.getName())).append(System.lineSeparator());
            builder.append(String.format(FINAL_EXPLORER_ENERGY, e.getEnergy())).append(System.lineSeparator());
            if (e.getSuitcase().getExhibits().isEmpty()) {
                builder.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, "None")).append(System.lineSeparator());
            } else {
                List<String> exhibits = new ArrayList<>(e.getSuitcase().getExhibits());
                builder.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, String.join(", ", exhibits))).append(System.lineSeparator());
            }
        });
        return builder.toString().trim();
    }
}
