package solid.impl;

import solid.enums.ReportLevel;
import solid.interfaces.Layout;

public class SimpleLayout implements Layout {


    @Override
    public String format(String time, String message, ReportLevel reportLevel) {
        return String.format("%s - %s - %s", time, reportLevel, message);
    }
}
