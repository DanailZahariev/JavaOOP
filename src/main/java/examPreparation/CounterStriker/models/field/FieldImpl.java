package examPreparation.CounterStriker.models.field;

import examPreparation.CounterStriker.models.players.CounterTerrorist;
import examPreparation.CounterStriker.models.players.Player;
import examPreparation.CounterStriker.models.players.Terrorist;

import java.util.Collection;
import java.util.stream.Collectors;

import static examPreparation.CounterStriker.common.OutputMessages.*;

public class FieldImpl implements Field {
    @Override
    public String start(Collection<Player> players) {
        Collection<Player> counterTerrorist = players.stream().filter(p -> p instanceof CounterTerrorist)
                .collect(Collectors.toList());

        Collection<Player> terrorist = players.stream().filter(p -> p instanceof Terrorist)
                .collect(Collectors.toList());

        while (counterTerrorist.stream().anyMatch(Player::isAlive) && terrorist.stream().anyMatch(Player::isAlive)) {

            for (Player terrorists : terrorist) {
                for (Player ct : counterTerrorist) {
                    ct.takeDamage(terrorists.getGun().fire());
                }
            }
            counterTerrorist = counterTerrorist.stream().filter(Player::isAlive).collect(Collectors.toList());

            for (Player ct : counterTerrorist) {
                for (Player terrorists : terrorist) {
                    terrorists.takeDamage(ct.getGun().fire());
                }
            }
            terrorist = terrorist.stream().filter(Player::isAlive).collect(Collectors.toList());
        }
        return terrorist.stream().anyMatch(Player::isAlive) ? TERRORIST_WINS : COUNTER_TERRORIST_WINS;
    }
}
