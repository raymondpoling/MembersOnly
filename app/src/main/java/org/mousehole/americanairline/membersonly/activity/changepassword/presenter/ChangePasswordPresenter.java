package org.mousehole.americanairline.membersonly.activity.changepassword.presenter;

import org.mousehole.americanairline.membersonly.util.EncryptedPreferences;

public class ChangePasswordPresenter implements ChangePasswordPresenterContract.ChangePasswordPresenter {

    private EncryptedPreferences encryptedPreferences;
    private ChangePasswordPresenterContract.ChangePasswordView changePasswordView;

    public ChangePasswordPresenter(ChangePasswordPresenterContract.ChangePasswordView changePasswordView) {
        this.changePasswordView = changePasswordView;
        encryptedPreferences = EncryptedPreferences.getEncryptedPreferences(changePasswordView.getContext());
    }

    @Override
    public void changePassword(String oldUsername, String newUsername, String oldPassword, String newPassword) {
        if(encryptedPreferences.comparePasswords(oldUsername, oldPassword)) {
            if(!newUsername.equals("")) {
                encryptedPreferences.changePreferences(oldUsername, newUsername, newPassword);
                changePasswordView.changePasswordSuccess("Changed username and password.");
            } else {
                encryptedPreferences.changePreferences(oldUsername, oldUsername, newPassword);
                changePasswordView.changePasswordSuccess("Changed password.");
            }
        } else {
            changePasswordView.changePasswordFailure("Could not change password, verify old password.");
        }
    }
}
