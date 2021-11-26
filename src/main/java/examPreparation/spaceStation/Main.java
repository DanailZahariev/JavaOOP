package examPreparation.spaceStation;

import examPreparation.spaceStation.core.Controller;
import examPreparation.spaceStation.core.ControllerImpl;
import examPreparation.spaceStation.core.Engine;
import examPreparation.spaceStation.core.EngineImpl;

public class Main {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();

    }
}
