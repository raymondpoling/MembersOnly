package org.mousehole.americanairline.membersonly.presenter;

public interface LoginContract {
    public interface LoginView {
        void displayLogin();
        void displayLoginFailure(String message);
        void displayLoginSuccess(String message);
    }
    public interface LoginPresenter {
        void validateLogin(String username, String password);
    }
}
