package in.curos.firechat.screens;

import android.content.Context;
import android.widget.FrameLayout;

/**
 * Created by curos on 13/04/17.
 */

public abstract class Screen<Controller extends Screen.ControllerContract> extends FrameLayout {

    protected Controller controller;

    public Screen(Context context, Controller controller) {
        super(context);
        this.controller = controller;
        initialize();
    }

    abstract public void initialize();

    interface ControllerContract {
    }
}
