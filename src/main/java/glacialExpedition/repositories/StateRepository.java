package glacialExpedition.repositories;

import glacialExpedition.models.states.State;

import java.util.*;

public class StateRepository implements Repository<State> {

    //    private List<State> states;
    private Map<String, State> stateMap;

    public StateRepository() {
        this.stateMap = new LinkedHashMap<>();
//        this.states = new ArrayList<>();
    }

    @Override
    public Collection<State> getCollection() {
        return Collections.unmodifiableCollection(this.stateMap.values());
//        return Collections.unmodifiableCollection(this.states);
    }

    @Override
    public void add(State entity) {
        stateMap.put(entity.getName(), entity);
//        states.add(entity);
    }

    @Override
    public boolean remove(State entity) {
        return this.stateMap.remove(entity.getName()) != null;
//        return states.remove(entity);
    }

    @Override
    public State byName(String name) {
        return this.stateMap.get(name);
//        return states.stream().filter(state -> state.getName().equals(name)).findFirst().orElse(null);
    }
}
