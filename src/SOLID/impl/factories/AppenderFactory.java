package SOLID.impl.factories;

import SOLID.enums.ReportLevel;
import SOLID.impl.ConsoleAppender;
import SOLID.impl.FileAppender;
import SOLID.interfaces.Appender;
import SOLID.interfaces.Factory;
import SOLID.interfaces.Layout;

public class AppenderFactory implements Factory<Appender> {

    private LayoutFactory layoutFactory;

    public AppenderFactory() {
        this.layoutFactory = new LayoutFactory();
    }

    @Override
    public Appender produce(String input) {
        String[] tokens = input.split(" ");

        String appenderType = tokens[0];
        String layoutType = tokens[1];

        Layout layout = layoutFactory.produce(layoutType);
        Appender appender = null;
        if (appenderType.equals("ConsoleAppender")) {
            appender = new ConsoleAppender(layout);
        } else if (appenderType.equals("FileAppender")) {
            appender = new FileAppender(layout);
        }

        if (tokens.length >= 3) {
            appender.setReportLevel(ReportLevel.valueOf(tokens[2]));
        }

        return appender;
    }
}
