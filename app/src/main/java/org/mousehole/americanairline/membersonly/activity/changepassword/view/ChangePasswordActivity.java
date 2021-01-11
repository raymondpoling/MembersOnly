package org.mousehole.americanairline.membersonly.activity.changepassword.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.mousehole.americanairline.membersonly.R;
import org.mousehole.americanairline.membersonly.activity.changepassword.presenter.ChangePasswordPresenter;
import org.mousehole.americanairline.membersonly.activity.changepassword.presenter.ChangePasswordPresenterContract;

public class ChangePasswordActivity extends AppCompatActivity implements ChangePasswordPresenterContract.ChangePasswordView {

    EditText newUsernameEditText, oldUsernameEditText, newPasswordEditText, oldPasswordEditText;
    Button commitButton;

    ChangePasswordPresenterContract.ChangePasswordPresenter changePasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        // bind
        newUsernameEditText = findViewById(R.id.new_username_edittext);
        oldUsernameEditText = findViewById(R.id.old_username_edittext);
        newPasswordEditText = findViewById(R.id.new_password_edittext);
        oldPasswordEditText = findViewById(R.id.old_password_edittext);
        commitButton = findViewById(R.id.commit_button);

        changePasswordPresenter = new ChangePasswordPresenter(this);

        commitButton.setOnClickListener(v -> {
            String newUsername = newUsernameEditText.getText().toString();
            String oldUsername = oldUsernameEditText.getText().toString();
            String passwordNew = newPasswordEditText.getText().toString();
            String passwordOld = oldPasswordEditText.getText().toString();

           changePasswordPresenter.changePassword(oldUsername, newUsername, passwordOld, passwordNew);
        });
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void changePasswordSuccess(String message) {
        displayToast(message);
        finish();
    }

    @Override
    public void changePasswordFailure(String message) {
        displayToast(message);
    }
}