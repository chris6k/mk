package com.jk.makemoney.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.jk.makemoney.MkApp;

/**
 * @author chris.xue
 */
public class UserProfile {
    private SharedPreferences sp;

    private UserProfile() {
        sp = MkApp.getContext().getSharedPreferences("userProfile", Context.MODE_PRIVATE);
        userId = sp.getString("userId", "");
        token = sp.getString("token", "");
    }

    private static UserProfile instance = new UserProfile();

    public static UserProfile getInstance() {
        return instance;
    }

    private volatile String userId = "";
    private volatile String token = "";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        sp.edit().putString("userId", userId).commit();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        sp.edit().putString("token", token).commit();
    }
}
