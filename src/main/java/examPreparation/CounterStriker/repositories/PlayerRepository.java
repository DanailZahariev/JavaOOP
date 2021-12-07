package examPreparation.CounterStriker.repositories;

import examPreparation.CounterStriker.models.players.Player;
import examPreparation.CounterStriker.common.ExceptionMessages;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerRepository implements Repository<Player> {

    private Map<String, Player> players;

    public PlayerRepository() {
        this.players = new LinkedHashMap<>();
    }

    @Override
    public Collection<Player> getModels() {
        return this.players.values();
    }

    @Override
    public void add(Player model) {
        if (model == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_REPOSITORY);
        }
        players.put(model.getUsername(), model);
    }

    @Override
    public boolean remove(Player model) {
        Player playerToRemove = players.remove(model);
        return playerToRemove != null;
    }

    @Override
    public Player findByName(String name) {
        return players.get(name);
    }
}
