package examPreparation.viceCity;

import examPreparation.viceCity.core.EngineImpl;
import examPreparation.viceCity.core.ControllerImpl;
import examPreparation.viceCity.core.interfaces.Controller;
import examPreparation.viceCity.core.interfaces.Engine;

public class Main {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
