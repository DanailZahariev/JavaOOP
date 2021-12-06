package examPreparation.glacialExpedition.models.mission;

import examPreparation.glacialExpedition.models.explorers.Explorer;
import examPreparation.glacialExpedition.models.states.State;

import java.util.Collection;

public interface Mission {
    void explore(State state, Collection<Explorer> explorers);
}
