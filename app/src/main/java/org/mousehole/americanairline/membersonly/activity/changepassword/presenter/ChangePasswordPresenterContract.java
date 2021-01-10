package org.mousehole.americanairline.membersonly.activity.changepassword.presenter;

public class ChangePasswordPresenterContract {
    public interface ChangePasswordPresenter {
        void changePassword(String oldPassword, String newPassword);
    }
    public interface ChagnePasswordView {
        void changePasswordSuccess(String message);
        void changePasswordFailure(String message);
    }
}
