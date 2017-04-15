package in.curos.firechat.screens;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.FrameLayout;

/**
 * Created by curos on 13/04/17.
 */

public abstract class Screen<Controller extends Screen.ControllerContract> extends FrameLayout {

    protected Controller controller;

    protected ProgressDialog loadingDialog;

    public Screen(Context context, Controller controller) {
        super(context);
        this.controller = controller;

        loadingDialog = new ProgressDialog(context);
        loadingDialog.setIndeterminate(true);
        loadingDialog.setMessage("Loading...");

        initialize();
    }

    public void showLoading() {
        loadingDialog.show();
    }

    public void hideLoading() {
        loadingDialog.hide();
    }

    abstract public void initialize();

    interface ControllerContract {
    }
}
