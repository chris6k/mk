package com.jk.makemoney.com.jk.makemoney.utils;

/**
 * @author chris.xue
 *         常量
 */
public final class Constants {
    public static final String API_HOST;
    public static final int USER_ID;

    private Constants() {
    }

    static {
        //TODO init data from properties files.
        API_HOST = "http://localhost/mk/api";
        USER_ID = 0;
    }
}
