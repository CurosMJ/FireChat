package in.curos.firechat.screens;

import android.content.Context;

import butterknife.ButterKnife;
import butterknife.OnClick;
import in.curos.firechat.R;

/**
 * Created by curos on 13/04/17.
 */

public class LoginScreen extends Screen<LoginScreen.LoginScreenController> {

    public LoginScreen(Context context, LoginScreenController loginScreenController) {
        super(context, loginScreenController);
    }

    public void initialize() {
        inflate(getContext(), R.layout.screen_login, this);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.sign_in)
    public void btnClick() {
        controller.initiateLogin();
    }

    public interface LoginScreenController extends Screen.ControllerContract {
        void initiateLogin();
    }
}
