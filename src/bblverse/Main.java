package bblverse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.net.URL;

import org.eclipse.swt.graphics.*;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;

public class Main {
    public static void main(String[] args) throws Exception {
        display = new Display();
        loadIcon();
        window = new MainWindow();
        trayItem = new BblTrayItem();
        Engine.setup();
        display.asyncExec(new Runnable() {
            @Override public void run() {
                trayItem.showNewVerse();
            }
        });
        trayItem.scheduleNewVerse();
        while (!window.shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
        Engine.shutdown();
        display.dispose();
    }

    static Image icon;
    static Display display;
    static MainWindow window;
    static BblTrayItem trayItem;


    protected static void loadIcon() {
        InputStream iconStream = null;
        iconStream = Main.class.getClassLoader().getResourceAsStream("bblverse/images/icon48.png");
        if (iconStream == null) {
            System.err.println("Could not find icon.");
            System.exit(1);
        }
        icon = new Image(Display.getDefault(), iconStream);
    }



}
