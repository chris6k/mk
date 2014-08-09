package com.jk.makemoney.com.jk.makemoney.utils;

/**
 * @author chris.xue
 */
public class UserProfile {

    private UserProfile() {
        //TODO
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
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
