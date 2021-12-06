package examPreparation.glacialExpedition;

import examPreparation.glacialExpedition.core.Controller;
import examPreparation.glacialExpedition.core.ControllerImpl;
import examPreparation.glacialExpedition.core.Engine;
import examPreparation.glacialExpedition.core.EngineImpl;

public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
