package org.mousehole.americanairline.membersonly.activity.login.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.security.crypto.EncryptedFile;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import org.mousehole.americanairline.membersonly.activity.changepassword.presenter.ChangePasswordPresenter;
import org.mousehole.americanairline.membersonly.activity.changepassword.presenter.ChangePasswordPresenterContract;
import org.mousehole.americanairline.membersonly.util.Constants;
import org.mousehole.americanairline.membersonly.util.EncryptedPreferences;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.mousehole.americanairline.membersonly.util.Constants.LOG_TAG;

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
