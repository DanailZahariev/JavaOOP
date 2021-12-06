package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;


public class MissionImpl implements Mission {


    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        Collection<String> exhibits = state.getExhibits();
        for (Explorer explorer : explorers) {

            while (explorer.canSearch() && exhibits.iterator().hasNext()) {

                String currentExhibit = exhibits.iterator().next();
                explorer.getSuitcase().getExhibits().add(currentExhibit);
                exhibits.remove(currentExhibit);
                explorer.search();
            }
        }
    }

//    @Override
//    public void explore(State state, Collection<Explorer> explorers) {
//        List<Explorer> explorerList = explorers.stream().filter(e -> e.getEnergy() > 0).collect(Collectors.toList());
//        for (int exp = 0; exp < explorerList.size(); exp++) {
//            Explorer explorer = explorerList.get(exp);
//            for (int index = 0; index < state.getExhibits().size(); index++) {
//                String exhibits = state.getExhibits().get(index);
//                explorer.search();
//                explorer.getSuitcase().getExhibits().add(exhibits);
//                state.getExhibits().remove(index);
//                index--;
//                if (!explorer.canSearch()) {
//                    explorerList.remove(exp);
//                    exp--;
//                    break;
//                }
//            }
//        }
//    }
}
