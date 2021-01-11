package org.mousehole.americanairline.membersonly.activity.changepassword.presenter;

import android.content.Context;

public interface ChangePasswordPresenterContract {
    interface ChangePasswordPresenter {
        void changePassword(String oldUsername, String newUsername, String oldPassword, String newPassword);
    }
    interface ChangePasswordView {
        Context getContext();
        void changePasswordSuccess(String message);
        void changePasswordFailure(String message);
    }
}
