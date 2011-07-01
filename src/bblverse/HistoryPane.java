package bblverse;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;

class HistoryPane {
    TabItem tabItem;
    Composite container;
    HistoryPane(TabFolder parent) {
        tabItem = new TabItem(parent, SWT.NONE);
        tabItem.setText("Előzmények");
        container = new Composite(parent, SWT.NONE);
        tabItem.setControl(container);
        createControls();
    }

    List list;
    Text text;

    private void createControls() {
        container.setLayout(new GridLayout(1, false));
        list = new List(container, SWT.BORDER | SWT.V_SCROLL );
        GridData listData = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
        list.setLayoutData(listData);

        list.addListener(SWT.Selection, new Listener() {
            @Override public void handleEvent(Event e) {
                Main.window.verseSelected(
                    Engine.history.get(Engine.history.size()-1-
                        list.getSelectionIndex()));
            }
        });

        text = new Text(container, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI | SWT.WRAP);

        GridData textData = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2);
        text.setLayoutData(textData);
        
        // TODO copy button
        // TODO share on Facebook button
    }
    

    void update() {
        int index = Engine.history.get(Engine.history.size()-1);
        list.add(Engine.verses.get(index),0);
        list.select(0);
        Main.window.verseSelected(index);
    }

    void verseSelected(int index) {
        text.setText(Engine.verses.get(index));
    }
}
