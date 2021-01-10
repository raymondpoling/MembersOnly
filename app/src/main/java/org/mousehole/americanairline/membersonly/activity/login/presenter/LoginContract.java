package org.mousehole.americanairline.membersonly.activity.login.presenter;

public interface LoginContract {
    interface LoginView {
        void displayLogin();
        void displayLoginFailure(String message);
        void displayLoginSuccess(String message);
    }
    interface LoginPresenter {
        void validateLogin(String username, String password);
    }
}
