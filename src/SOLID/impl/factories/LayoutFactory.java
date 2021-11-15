package SOLID.impl.factories;

import SOLID.impl.SimpleLayout;
import SOLID.impl.XmlLayout;
import SOLID.interfaces.Factory;
import SOLID.interfaces.Layout;

public class LayoutFactory implements Factory<Layout> {

    @Override
    public Layout produce(String input) {
        Layout layout = null;

        if (input.equals("SimpleLayout")) {
            layout = new SimpleLayout();
        } else if (input.equals("XmlLayout")) {
            layout = new XmlLayout();
        }

        return layout;
    }
}
