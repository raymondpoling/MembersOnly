package org.mousehole.americanairline.membersonly.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import org.mousehole.americanairline.membersonly.activity.login.presenter.LoginContract;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.mousehole.americanairline.membersonly.util.Constants.LOG_TAG;

public class EncryptedPreferences {

    private SharedPreferences sharedPreferences;
    private static EncryptedPreferences encryptedPreferences = null;

    private EncryptedPreferences(Context context) {
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
        } catch (GeneralSecurityException | IOException e) {
            Log.wtf(LOG_TAG, e.getMessage());
        }
    }

    public static EncryptedPreferences getEncryptedPreferences(Context context) {
        if(encryptedPreferences == null) {
            encryptedPreferences = new EncryptedPreferences(context);
        }
        return encryptedPreferences;
    }

    public boolean comparePasswords(String username, String password) {
        return password.equals(sharedPreferences.getString(username,"username"));
    }

    public void changePreferences(String oldUsername, String newUsername, String newPassword) {
        sharedPreferences.edit()
                .remove(oldUsername)
                .putString(newUsername, newPassword)
                .apply();;
    }

}
