package com.jk.makemoney.com.jk.makemoney.utils;

/**
 * @author kun
 */
public enum PlatformEnum {
    YOUMI(1);

    private PlatformEnum(int code) {
        this.code = code;
    }

    private final int code;

    public int getCode() {
        return code;
    }
}
