package examPreparation.viceCity.core;

import examPreparation.viceCity.common.ConstantMessages;
import examPreparation.viceCity.core.interfaces.Controller;
import examPreparation.viceCity.models.guns.Gun;
import examPreparation.viceCity.models.guns.Pistol;
import examPreparation.viceCity.models.guns.Rifle;
import examPreparation.viceCity.models.neighbourhood.GangNeighbourhood;
import examPreparation.viceCity.models.neighbourhood.Neighbourhood;
import examPreparation.viceCity.models.players.CivilPlayer;
import examPreparation.viceCity.models.players.MainPlayer;
import examPreparation.viceCity.models.players.Player;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Player mainPlayer;
    private Map<String, Player> players;
    private ArrayDeque<Gun> guns;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.players = new LinkedHashMap<>();
        this.guns = new ArrayDeque<>();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        players.put(name, new CivilPlayer(name));
        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;

        switch (type) {
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default:
                return ConstantMessages.GUN_TYPE_INVALID;
        }
        guns.offer(gun);
        return String.format(ConstantMessages.GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        Gun gun = guns.poll();
        if (gun == null) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }

        if (name.equals("Vercetti")) {
            this.mainPlayer.getGunRepository().add(gun);
            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), mainPlayer.getName());
        }

        if (!players.containsKey(name)) {
            return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
        }

        this.players.get(name).getGunRepository().add(gun);
        return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
    }

    @Override
    public String fight() {
        neighbourhood.action(mainPlayer, players.values());
        if (mainPlayer.getLifePoints() == 100 && players.values().stream().allMatch(p -> p.getLifePoints() == 50)) {
            return ConstantMessages.FIGHT_HOT_HAPPENED;
        }

        List<Player> deadPlayers = players.values().stream().filter(player -> !player.isAlive()).collect(Collectors.toList());

        StringBuilder builder = new StringBuilder(ConstantMessages.FIGHT_HAPPENED);

        builder.append(System.lineSeparator()).append(String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints()))
                .append(System.lineSeparator());
        builder.append(String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deadPlayers.size())).append(System.lineSeparator());
        builder.append(String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, players.size() - deadPlayers.size()));

        for (Player deadPlayer : deadPlayers) {
            players.remove(deadPlayer.getName());
        }

        return builder.toString().trim();
    }
}
