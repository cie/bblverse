package bblverse;

import java.util.*;

import org.eclipse.swt.events.*;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;

public class BblTrayItem {
    TrayItem trayItem;
    ToolTip toolTip;

    public BblTrayItem() {
        trayItem = new TrayItem(Main.display.getSystemTray(), 0);
        trayItem.setToolTipText("BblVerse");
        trayItem.setImage(Main.icon);
        trayItem.setVisible(true);
        trayItem.addSelectionListener(new SelectionListener() {
            @Override public void widgetDefaultSelected(SelectionEvent e) {
                Main.window.shell.setVisible(true);
            }
            @Override public void widgetSelected(SelectionEvent e) {
                toolTip.setVisible(!toolTip.isVisible());
            }
        });
        toolTip = new ToolTip(Main.window.shell, SWT.BALLOON);
        trayItem.setToolTip(toolTip);
        toolTip.setAutoHide(false);
        createMenu();
    }

    void showNewVerse() {
        Engine.fetchNewVerse();
        toolTip.setText(Engine.cite);
        toolTip.setMessage(Engine.verse);
        toolTip.setVisible(true);
        Engine.timer.schedule(new TimerTask() {
            @Override public void run() {
                Main.display.asyncExec(new Runnable() {
                    @Override public void run() {
                        toolTip.setVisible(false);
                    }
                });
            }
        }, Engine.readingTime());

        scheduleNewVerse();
    }

    void scheduleNewVerse() {
        Engine.timer.schedule(new TimerTask() {
            @Override public void run() {
                Main.display.asyncExec(new Runnable() {
                    @Override public void run() {
                        showNewVerse();
                    }
                });
            }
        }, Engine.waitingTime());
    }


    Menu menu;
    private MenuItem quit, showVerse;

    void createMenu() {
        menu = new Menu(Main.window.shell, SWT.POP_UP);
        showVerse = new MenuItem(menu, SWT.PUSH);
        quit = new MenuItem(menu, SWT.PUSH); 
        
        showVerse.setText("Új bibliavers");
        quit.setText("Kilépés");

        showVerse.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {
                showNewVerse();
            }
        });
        quit.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {
                Main.window.shell.dispose();
            }
        });

        trayItem.addListener(SWT.MenuDetect, new Listener() {
            public void handleEvent(Event e) {
                menu.setVisible(true);
            }
        });
    }



}
