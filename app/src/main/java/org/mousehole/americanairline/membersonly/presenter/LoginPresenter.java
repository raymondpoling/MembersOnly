package org.mousehole.americanairline.membersonly.presenter;

public class LoginPresenter implements LoginContract.LoginPresenter {

    LoginContract.LoginView loginView;

    public LoginPresenter(LoginContract.LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void validateLogin(String username, String password) {
        loginView.displayLoginSuccess("Login temporarily locked on.");
    }
}
