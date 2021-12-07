package examPreparation.CounterStriker;

import examPreparation.CounterStriker.core.EngineImpl;
import examPreparation.CounterStriker.core.Engine;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
