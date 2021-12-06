package examPreparation.glacialExpedition.core;

import examPreparation.glacialExpedition.common.ConstantMessages;
import examPreparation.glacialExpedition.common.ExceptionMessages;
import examPreparation.glacialExpedition.models.explorers.AnimalExplorer;
import examPreparation.glacialExpedition.models.explorers.GlacierExplorer;
import examPreparation.glacialExpedition.models.mission.MissionImpl;
import examPreparation.glacialExpedition.models.states.StateImpl;
import examPreparation.glacialExpedition.repositories.ExplorerRepository;
import examPreparation.glacialExpedition.repositories.StateRepository;
import examPreparation.glacialExpedition.models.explorers.Explorer;
import examPreparation.glacialExpedition.models.explorers.NaturalExplorer;
import examPreparation.glacialExpedition.models.mission.Mission;
import examPreparation.glacialExpedition.models.states.State;
import examPreparation.glacialExpedition.repositories.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Repository<Explorer> explorerRepository;
    private Repository<State> stateRepository;
    private int exploredStates;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;
        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }
        explorerRepository.add(explorer);
        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        Collection<String> stateExhibits = state.getExhibits();
        Collections.addAll(stateExhibits, exhibits);
        this.stateRepository.add(state);
        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        if (explorer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        explorerRepository.remove(explorer);
        return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        List<Explorer> explorers = explorerRepository.getCollection().stream().filter(e -> e.getEnergy() > 50).collect(Collectors.toList());
        if (explorers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        State state = stateRepository.byName(stateName);
        Mission mission = new MissionImpl();
        mission.explore(state, explorers);
        long retired = explorers.stream().filter(e -> e.getEnergy() == 0).count();
        this.exploredStates++;
        return String.format(ConstantMessages.STATE_EXPLORER, stateName, retired);
    }

    @Override
    public String finalResult() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(ConstantMessages.FINAL_STATE_EXPLORED, this.exploredStates)).append(System.lineSeparator());
        builder.append(ConstantMessages.FINAL_EXPLORER_INFO);
        Collection<Explorer> explorers = this.explorerRepository.getCollection();
        for (Explorer explorer : explorers) {
            builder.append(System.lineSeparator());
            builder.append(String.format(ConstantMessages.FINAL_EXPLORER_NAME, explorer.getName()));
            builder.append(System.lineSeparator());
            builder.append(String.format(ConstantMessages.FINAL_EXPLORER_ENERGY, explorer.getEnergy()));
            builder.append(System.lineSeparator());
            if (explorer.getSuitcase().getExhibits().isEmpty()) {
                builder.append(String.format(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS, "None"));
            } else {
                builder.append(String.format(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS, String.join(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER, explorer.getSuitcase().getExhibits())));
            }
        }
        return builder.toString().trim();
//        StringBuilder builder = new StringBuilder();
//        builder.append(String.format(FINAL_STATE_EXPLORED, exploredStates)).append(System.lineSeparator());
//        builder.append(FINAL_EXPLORER_INFO).append(System.lineSeparator());
//        this.explorerRepository.getCollection().forEach(e -> {
//            builder.append(String.format(FINAL_EXPLORER_NAME, e.getName())).append(System.lineSeparator());
//            builder.append(String.format(FINAL_EXPLORER_ENERGY, e.getEnergy())).append(System.lineSeparator());
//            if (e.getSuitcase().getExhibits().isEmpty()) {
//                builder.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, "None")).append(System.lineSeparator());
//            } else {
//                builder.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, String.join(", ", e.getSuitcase().getExhibits()))).append(System.lineSeparator());
//            }
//        });
    }
}
