package org.mousehole.americanairline.membersonly.activity.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.mousehole.americanairline.membersonly.R;
import org.mousehole.americanairline.membersonly.activity.listdisplay.view.MemberListActivity;
import org.mousehole.americanairline.membersonly.activity.login.presenter.LoginContract;
import org.mousehole.americanairline.membersonly.activity.login.presenter.LoginPresenter;
import org.mousehole.americanairline.membersonly.util.Constants;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    EditText usernameEditText, passwordEditText;
    Button loginButton;

    LoginContract.LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.new_username_edittext);
        passwordEditText = findViewById(R.id.new_password_edittext);
        loginButton = findViewById(R.id.login_button);

        loginPresenter = new LoginPresenter(this);

        displayLogin();
    }

    @Override
    public void displayLogin() {
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            loginPresenter.validateLogin(username, password);
        });
    }

    private void toast(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayLoginFailure(String message) {
        toast(message);
    }

    @Override
    public void displayLoginSuccess(String message) {
        toast(message);
        Intent start = new Intent(this, MemberListActivity.class);
        startActivity(start);
        finish();
        Log.e(Constants.LOG_TAG, "Finish was obviously ran");
    }

    @Override
    public Context getContext() {
        return this;
    }
}