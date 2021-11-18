package ReflectionExercise.barracksWars;

import ReflectionExercise.barracksWars.core.Engine;
import ReflectionExercise.barracksWars.core.factories.UnitFactoryImpl;
import ReflectionExercise.barracksWars.data.UnitRepository;
import ReflectionExercise.barracksWars.interfaces.Repository;
import ReflectionExercise.barracksWars.interfaces.Runnable;
import ReflectionExercise.barracksWars.interfaces.UnitFactory;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
