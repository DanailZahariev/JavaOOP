package reflectionExercise.barracksWars.core.commands;

import reflectionExercise.barracksWars.interfaces.Repository;
import reflectionExercise.barracksWars.interfaces.UnitFactory;

public class Fight extends Command {

    public Fight(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        return "fight";
    }
}
