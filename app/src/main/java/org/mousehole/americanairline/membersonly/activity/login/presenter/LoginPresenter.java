package org.mousehole.americanairline.membersonly.activity.login.presenter;

import org.mousehole.americanairline.membersonly.util.EncryptedPreferences;

public class LoginPresenter implements LoginContract.LoginPresenter {

    LoginContract.LoginView loginView;
    EncryptedPreferences encryptedPreferences;

    public LoginPresenter(LoginContract.LoginView loginView) {
        this.loginView = loginView;
        encryptedPreferences = EncryptedPreferences.getEncryptedPreferences(loginView.getContext());
    }

    @Override
    public void validateLogin(String username, String password) {
        if(encryptedPreferences.comparePasswords(username, password)) {
            loginView.displayLoginSuccess("Welcome back " + username);
        } else {
            loginView.displayLoginFailure("Username/password incorrect.");
        }
    }
}
