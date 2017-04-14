package in.curos.firechat.screens;

import android.app.ProgressDialog;
import android.content.Context;

import butterknife.ButterKnife;
import butterknife.OnClick;
import in.curos.firechat.R;

/**
 * Created by curos on 13/04/17.
 */

public class LoginScreen extends Screen<LoginScreen.LoginScreenController> {

    private ProgressDialog loadingDialog;

    public LoginScreen(Context context, LoginScreenController loginScreenController) {
        super(context, loginScreenController);

        loadingDialog = new ProgressDialog(context);
        loadingDialog.setIndeterminate(true);
        loadingDialog.setMessage("Loading...");
    }

    public void initialize() {
        inflate(getContext(), R.layout.screen_login, this);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.sign_in)
    public void btnClick() {
        controller.initiateLogin();
    }

    public void showLoading() {
        loadingDialog.show();
    }

    public void hideLoading() {
        loadingDialog.hide();
    }

    public interface LoginScreenController extends Screen.ControllerContract {
        void initiateLogin();
    }
}
