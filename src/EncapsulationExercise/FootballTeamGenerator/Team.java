package EncapsulationExercise.FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        setName(name);
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        } else {
            this.name = name.trim();
        }
    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String playerName) {
        boolean removeIf = this.players.removeIf(player -> player.getName().equals(playerName));
        if (!removeIf) {
            throw new IllegalArgumentException("Player " + playerName + " is not in " + name + " team.");
        }
    }

    public double getRating() {
        return players.stream().mapToDouble(Player::overallSkillLevel).average().orElse(0.00);
    }

    public boolean hasPlayer(String name) {
        return players.stream().anyMatch(player -> name.equals(player.getName()));
    }
}
