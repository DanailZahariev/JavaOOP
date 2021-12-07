package examPreparation.viceCity.models.neighbourhood;

import examPreparation.viceCity.models.players.Player;

import java.util.Collection;

public interface Neighbourhood {
    void action(Player mainPlayer, Collection<Player> civilPlayers);
}
