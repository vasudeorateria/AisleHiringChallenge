package com.kjstudios.aislehiringchallenge.data;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {

    private SharedPreferences sharedPreferences;
    private String PREF_NAME = "USER_DATA";
    private String USER_TOKEN_KEY = "USER_TOKEN";

    public UserPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        }
    }

    public void addToken(String token) {
        sharedPreferences.edit().putString(USER_TOKEN_KEY, token).apply();
    }

    public String getToken() {
        return sharedPreferences.getString(USER_TOKEN_KEY, null);
    }
}
