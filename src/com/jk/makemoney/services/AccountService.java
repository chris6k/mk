package com.jk.makemoney.services;

import com.jk.makemoney.beans.Account;

/**
 * @author chris.xue
 *         账户service层
 */
public class AccountService {

    /**
     * 根据用户ID获取账户信息
     *
     * @param userId
     * @return
     */
    public Account readAccountById(int userId) {
        //TODO
        return null;
    }

    /**
     * 登录
     */
    public String signIn() {
        //TODO
        return null;
    }

    /**
     * 登出
     *
     * @param token
     * @return
     */
    public boolean signOut(String token) {
        //TODO
        return true;
    }
}
