package glacialExpedition.repositories;

import glacialExpedition.models.explorers.Explorer;

import java.util.*;

public class ExplorerRepository implements Repository<Explorer> {

    //    private List<Explorer> explorers;
    private Map<String, Explorer> explorers;

    public ExplorerRepository() {
        this.explorers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Explorer> getCollection() {
        return Collections.unmodifiableCollection(this.explorers.values());
//        return Collections.unmodifiableCollection(this.explorers);
    }

    @Override
    public void add(Explorer entity) {
        explorers.put(entity.getName(), entity);
//        explorers.add(entity);
    }

    @Override
    public boolean remove(Explorer entity) {
        return explorers.remove(entity.getName()) != null;
//        return this.explorers.remove(entity);
    }

    @Override
    public Explorer byName(String name) {
        return explorers.get(name);
//        return explorers.stream().filter(explorer -> explorer.getName().equals(name)).findFirst().orElse(null);
    }
}
