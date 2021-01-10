package org.mousehole.americanairline.membersonly.activity.login.presenter;

import android.content.Context;

public interface LoginContract {
    interface LoginView {
        void displayLogin();
        void displayLoginFailure(String message);
        void displayLoginSuccess(String message);
        Context getContext();
    }
    interface LoginPresenter {
        void validateLogin(String username, String password);
    }
}
