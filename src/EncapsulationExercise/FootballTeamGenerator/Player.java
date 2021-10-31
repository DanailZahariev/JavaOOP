package EncapsulationExercise.FootballTeamGenerator;

public class Player {
    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        setName(name);
        setEndurance(endurance);
        setSprint(sprint);
        setDribble(dribble);
        setPassing(passing);
        setShooting(shooting);
    }

    private void setShooting(int shooting) {
        if (shooting < 0 || shooting > 100) {
            throw new IllegalArgumentException("Shooting should be between 0 and 100.");
        } else {
            this.shooting = shooting;
        }
    }

    private void setPassing(int passing) {
        if (passing < 0 || passing > 100) {
            throw new IllegalArgumentException("Passing should be between 0 and 100.");
        } else {
            this.passing = passing;
        }
    }

    private void setDribble(int dribble) {
        if (dribble < 0 || dribble > 100) {
            throw new IllegalArgumentException("Dribble should be between 0 and 100.");
        } else {
            this.dribble = dribble;
        }
    }

    private void setSprint(int sprint) {
        if (sprint < 0 || sprint > 100) {
            throw new IllegalArgumentException("Sprint should be between 0 and 100.");
        } else {
            this.sprint = sprint;
        }
    }

    private void setEndurance(int endurance) {
        if (endurance < 0 || endurance > 100) {
            throw new IllegalArgumentException("Endurance should be between 0 and 100.");
        } else {
            this.endurance = endurance;
        }
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        } else {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    public double overallSkillLevel() {
        return (this.shooting + this.endurance + this.dribble + this.passing + this.sprint) / 5.0;
    }
}
