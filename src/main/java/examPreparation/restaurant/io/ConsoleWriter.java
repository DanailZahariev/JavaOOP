package examPreparation.restaurant.io;

import examPreparation.restaurant.io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {

    public void writeLine(String text) {
        System.out.println(text);
    }
}
