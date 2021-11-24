package encapsulationExercise.FootballTeamGenerator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Team> teams = new LinkedHashMap<>();
        String command = scanner.nextLine();
        while (!command.equals("END")) {
            String[] tokens = command.split(";");
            String commands = tokens[0];
            try {
                switch (commands) {
                    case "Team":
                        teams.putIfAbsent(tokens[1], new Team(tokens[1]));
                        break;
                    case "Add":
                        addPlayer(teams, tokens);
                        break;
                    case "Remove":
                        removePlayer(teams, tokens);
                        break;
                    case "Rating":
                        if (teams.containsKey(tokens[1])) {
                            int rating = (int) Math.round(teams.get(tokens[1]).getRating());
                            System.out.println(tokens[1] + " - " + rating);
                        } else {
                            System.out.println("Team " + tokens[1] + " does not exist.");
                        }
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            command = scanner.nextLine();
        }
    }

    private static void removePlayer(Map<String, Team> teams, String[] tokens) {
        if (teams.get(tokens[1]).hasPlayer(tokens[2])) {
            teams.get(tokens[1]).removePlayer(tokens[2]);
        } else {
            System.out.println("Player " + tokens[2] + " is not in "
                    + tokens[1] + " team.");
        }
    }

    private static void addPlayer(Map<String, Team> teams, String[] tokens) {
        if (teams.containsKey(tokens[1])) {
            Player player = new Player(tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]),
                    Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]));
            teams.get(tokens[1]).addPlayer(player);
        } else {
            System.out.println("Team " + tokens[1] + " does not exist.");
        }
    }
}
