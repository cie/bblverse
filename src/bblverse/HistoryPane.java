package bblverse;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;

class HistoryPane extends VersesPane {

    HistoryPane(TabFolder parent) {
        super(parent, "Előzmények");

        list.addListener(SWT.Selection, new Listener() {
            @Override public void handleEvent(Event e) {
                Main.window.verseSelected(
                    Engine.history.get(Engine.history.size()-1-
                        list.getSelectionIndex()));
            }
        });

        
    }
    

    @Override void update() {
        int index = Engine.history.get(Engine.history.size()-1);
        list.add(Engine.verses.get(index),0);
        list.select(0);
        Main.window.verseSelected(index);
    }


    @Override void verseSelected(int index) {
        text.setText(Engine.verses.get(index));
    }

}
