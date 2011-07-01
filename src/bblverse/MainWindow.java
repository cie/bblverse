package bblverse;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;

class MainWindow {
    Shell shell;
    HistoryPane history;

    MainWindow() {
        shell = new Shell(Main.display, SWT.BORDER | SWT.CLOSE | SWT.MIN | SWT.RESIZE | SWT.TITLE );
        shell.setSize(450,300);
        shell.setLocation(
                (Main.display.getClientArea().width - shell.getSize().x) / 2,
                (Main.display.getClientArea().height- shell.getSize().y) / 2);
        shell.setImage(Main.icon);
        shell.setText("BblVerse");
        shell.addListener(SWT.Close, new Listener() {
            @Override public void handleEvent(Event event) {
                shell.setVisible(false);
                event.doit = false;
            }
        });
        createTabs();
    }

    TabFolder tabs;
    HistoryPane historyPane;

    private void createTabs() {
        tabs = new TabFolder(shell, SWT.TOP);
        historyPane = new HistoryPane(tabs);
    }
    private void createHistoryPane(Composite parent) {
        GridLayout layout = new GridLayout(1, false);
        parent.setLayout(layout);

    }
}