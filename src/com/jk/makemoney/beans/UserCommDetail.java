package com.jk.makemoney.beans;

import java.util.Date;

/**
 * @author chris.xue
 *         收入
 */
public class UserCommDetail extends UserBaseDetail {
    private static final long serialVersionUID = 7414335109283313922L;

    public UserCommDetail() {
    }

    public UserCommDetail(Date dateDay, String detail, int amount) {
        super(dateDay, detail, amount);
    }
}
