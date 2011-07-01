package bblverse;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;

abstract class Pane {

    TabItem tabItem;
    Composite container;
    Pane(TabFolder parent, String title) {
        tabItem = new TabItem(parent, SWT.NONE);
        tabItem.setText(title);
        container = new Composite(parent, SWT.NONE);
        tabItem.setControl(container);
    }

    abstract void verseSelected(int index);
    abstract void update();

}
