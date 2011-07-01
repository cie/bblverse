package bblverse;

import java.util.*;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;

class ContextPane extends VersesPane {
    int startIndex = -1;

    ContextPane(TabFolder parent) {
        super(parent, "Környezet");
        list.addListener(SWT.Selection, new Listener() {
            @Override public void handleEvent(Event e) {
                if (startIndex != -1) {
                    Main.window.verseSelected(startIndex + list.getSelectionIndex());
                }
            }
        });

    }



    @Override void update() {
        update(Engine.index);
    }

    private void update(int index) {
        if (index < Engine.contextVerses) {
            startIndex = 0;
        } else {
            startIndex = index - Engine.contextVerses;
        }
        list.removeAll();
        int end = index+Engine.contextVerses + 1 < Engine.verses.size() ? 
            index+Engine.contextVerses + 1 : 
            Engine.verses.size();
        for (int i = startIndex; i<end; ++i) {
            list.add(Engine.verses.get(i));
        }
        int itemHeight = list.getItemHeight();
        int lastSeen = index +
            list.getClientArea().height / itemHeight / 2;
        list.select(lastSeen);
        list.showSelection();
        list.select(index);
    };

    TimerTask updateTask;

    @Override void verseSelected(final int index) {
        text.setText(Engine.verses.get(index));
        if (updateTask != null) {
            updateTask.cancel();
        }
        updateTask = new TimerTask() {
            @Override public void run() {
                update(index);
                updateTask = null;
            }
        };
        Engine.timer.schedule(updateTask, 1000);
    }

}
