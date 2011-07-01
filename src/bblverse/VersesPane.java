package bblverse;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;

abstract class VersesPane extends Pane {

    VersesPane(TabFolder parent, String title) {
        super(parent, title);
        container.setLayout(new GridLayout(1, false));
        list = new List(container, SWT.BORDER | SWT.V_SCROLL );
        GridData listData = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
        list.setLayoutData(listData);

        text = new Text(container, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI | SWT.WRAP);

        GridData textData = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2);
        text.setLayoutData(textData);
        // TODO copy button
        // TODO share on Facebook button
        // TODO save to favorites button

    }

    List list;
    Text text;


}
