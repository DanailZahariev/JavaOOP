package reflectionExercise.barracksWars;

import reflectionExercise.barracksWars.core.Engine;
import reflectionExercise.barracksWars.core.factories.UnitFactoryImpl;
import reflectionExercise.barracksWars.data.UnitRepository;
import reflectionExercise.barracksWars.interfaces.Repository;
import reflectionExercise.barracksWars.interfaces.Runnable;
import reflectionExercise.barracksWars.interfaces.UnitFactory;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
