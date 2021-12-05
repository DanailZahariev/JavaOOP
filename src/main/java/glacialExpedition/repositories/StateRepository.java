package glacialExpedition.repositories;

import glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StateRepository implements Repository<State> {

    private List<State> states;

    public StateRepository() {
        this.states = new ArrayList<>();
    }

    @Override
    public List<State> getCollection() {
        return Collections.unmodifiableList(this.states);
    }

    @Override
    public void add(State entity) {
        if (states.stream().noneMatch(e -> e.getName().equals(entity.getName()))) {
            states.add(entity);
        }
    }

    @Override
    public boolean remove(State entity) {
        return states.remove(entity);
    }

    @Override
    public State byName(String name) {
        return states.stream().filter(state -> state.getName().equals(name)).findFirst().orElse(null);
    }
}
