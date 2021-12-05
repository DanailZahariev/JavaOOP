package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission {

    @Override
    public void explore(State state, List<Explorer> explorers) {
        List<Explorer> explorerList = explorers.stream().filter(e -> e.getEnergy() > 0).collect(Collectors.toList());
        for (int exp = 0; exp < explorerList.size(); exp++) {
            Explorer explorer = explorerList.get(exp);
            for (int index = 0; index < state.getExhibits().size(); index++) {
                String exhibits = state.getExhibits().get(index);
                explorer.search();
                explorer.getSuitcase().getExhibits().add(exhibits);
                state.getExhibits().remove(index);
                index--;
                if (!explorer.canSearch()) {
                    explorerList.remove(exp);
                    exp--;
                    break;
                }
            }
        }
    }
}
