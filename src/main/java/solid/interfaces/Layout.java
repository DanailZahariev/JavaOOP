package solid.interfaces;

import solid.enums.ReportLevel;

public interface Layout {

    String format(String time, String message, ReportLevel reportLevel);

}
