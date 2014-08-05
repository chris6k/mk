package com.jk.makemoney.beans;

import java.io.Serializable;

/**
 * @author chris.xue
 *         账户
 */
public class Account implements Serializable {
    private static final long serialVersionUID = -5380180340817505034L;
    //用户ID
    private int id;
    //用户名
    private String username;
    //用户余额
    private int balance;
    //今日任务收入
    private int todayTaskCommission;
    //今日好友分成收入
    private int todayFriendCommission;
    //昨日任务收入
    private int yestTaskCommission;
    //昨日好友分成收入
    private int yestFriendCommission;

    public Account(int id, String username, int balance, int todayTaskCommission, int todayFriendCommission, int yestTaskCommission, int yestFriendCommission) {
        this.id = id;
        this.username = username;
        this.balance = balance;
        this.todayTaskCommission = todayTaskCommission;
        this.todayFriendCommission = todayFriendCommission;
        this.yestTaskCommission = yestTaskCommission;
        this.yestFriendCommission = yestFriendCommission;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getTodayTaskCommission() {
        return todayTaskCommission;
    }

    public void setTodayTaskCommission(int todayTaskCommission) {
        this.todayTaskCommission = todayTaskCommission;
    }

    public int getTodayFriendCommission() {
        return todayFriendCommission;
    }

    public void setTodayFriendCommission(int todayFriendCommission) {
        this.todayFriendCommission = todayFriendCommission;
    }

    public int getYestTaskCommission() {
        return yestTaskCommission;
    }

    public void setYestTaskCommission(int yestTaskCommission) {
        this.yestTaskCommission = yestTaskCommission;
    }

    public int getYestFriendCommission() {
        return yestFriendCommission;
    }

    public void setYestFriendCommission(int yestFriendCommission) {
        this.yestFriendCommission = yestFriendCommission;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                ", todayTaskCommission=" + todayTaskCommission +
                ", todayFriendCommission=" + todayFriendCommission +
                ", yestTaskCommission=" + yestTaskCommission +
                ", yestFriendCommission=" + yestFriendCommission +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != account.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}