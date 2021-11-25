package examPreparation.aquarium;


import examPreparation.aquarium.core.Engine;
import examPreparation.aquarium.core.EngineImpl;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
