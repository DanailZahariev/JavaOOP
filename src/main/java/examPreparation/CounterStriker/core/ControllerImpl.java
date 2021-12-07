package examPreparation.CounterStriker.core;

import examPreparation.CounterStriker.models.field.Field;
import examPreparation.CounterStriker.models.field.FieldImpl;
import examPreparation.CounterStriker.models.guns.Gun;
import examPreparation.CounterStriker.models.guns.Pistol;
import examPreparation.CounterStriker.models.guns.Rifle;
import examPreparation.CounterStriker.models.players.CounterTerrorist;
import examPreparation.CounterStriker.models.players.Player;
import examPreparation.CounterStriker.models.players.Terrorist;
import examPreparation.CounterStriker.repositories.GunRepository;
import examPreparation.CounterStriker.repositories.PlayerRepository;
import examPreparation.CounterStriker.common.ExceptionMessages;
import examPreparation.CounterStriker.common.OutputMessages;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private GunRepository gunRepository;
    private PlayerRepository playerRepository;
    private Field field;

    public ControllerImpl() {
        this.gunRepository = new GunRepository();
        this.playerRepository = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun;
        if (type.equals("Pistol")) {
            gun = new Pistol(name, bulletsCount);
        } else if (type.equals("Rifle")) {
            gun = new Rifle(name, bulletsCount);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_TYPE);
        }
        gunRepository.add(gun);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_GUN, name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        Gun gun = gunRepository.findByName(gunName);
        if (gun == null) {
            throw new NullPointerException(ExceptionMessages.GUN_CANNOT_BE_FOUND);
        }
        Player player;
        if (type.equals("CounterTerrorist")) {
            player = new CounterTerrorist(username, health, armor, gun);
        } else if (type.equals("Terrorist")) {
            player = new Terrorist(username, health, armor, gun);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }
        playerRepository.add(player);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        return field.start(playerRepository.getModels());
    }

    @Override
    public String report() {
        StringBuilder builder = new StringBuilder();

        List<Player> terrorist = playerRepository.getModels().stream().filter(p -> p.getClass().getSimpleName().equals("Terrorist"))
                .collect(Collectors.toList());

        List<Player> counterTerrorist = playerRepository.getModels()
                .stream()
                .filter(p -> p.getClass().getSimpleName().equals("CounterTerrorist"))
                .collect(Collectors.toList());

        List<Player> terroristSorted = terrorist.stream().sorted(Comparator.comparing(Player::getHealth).thenComparing(Player::getUsername))
                .collect(Collectors.toList());

        List<Player> counterTerroristSorted = counterTerrorist.stream().sorted(Comparator.comparing(Player::getHealth).thenComparing(Player::getUsername))
                .collect(Collectors.toList());

        for (Player player : counterTerroristSorted) {
            builder.append(player.toString()).append(System.lineSeparator());
        }
        for (Player player : terroristSorted) {
            builder.append(player.toString()).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
