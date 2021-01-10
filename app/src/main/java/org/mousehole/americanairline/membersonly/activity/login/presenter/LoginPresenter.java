package org.mousehole.americanairline.membersonly.activity.login.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.security.crypto.EncryptedFile;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import org.mousehole.americanairline.membersonly.util.Constants;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.mousehole.americanairline.membersonly.util.Constants.LOG_TAG;

public class LoginPresenter implements LoginContract.LoginPresenter {

    LoginContract.LoginView loginView;
    SharedPreferences sharedPreferences;

    public LoginPresenter(LoginContract.LoginView loginView) {
        this.loginView = loginView;
        Context context = loginView.getContext();
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String alias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
                sharedPreferences = EncryptedSharedPreferences.create(Constants.APPLICATION_PREFERENCES,
                        alias,
                        context,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                );
            }
        } catch (GeneralSecurityException e) {
            Log.wtf(LOG_TAG, e.getMessage());
        } catch (IOException e) {
            Log.wtf(LOG_TAG, e.getMessage());
        }



    }

    @Override
    public void validateLogin(String username, String password) {
        String actualUsername = sharedPreferences.getString("username", "password");
        String actualPassword = sharedPreferences.getString(username, "username");
        if(actualUsername.equals(username) && password.equals(actualPassword) && !password.equals("")) {
            loginView.displayLoginSuccess("Welcome back " + username);
        } else {
            loginView.displayLoginFailure("Username/password incorrect.");
        }
    }
}
