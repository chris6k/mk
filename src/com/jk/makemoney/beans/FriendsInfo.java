package com.jk.makemoney.beans;

import java.io.Serializable;

/**
 * @author chris.xue
 *         好友信息
 */
public class FriendsInfo implements Serializable {
    private static final long serialVersionUID = -3164297712469841373L;
    //好友总数
    private int friendCount;
    //上月好友分成
    private int lastMonthCommission;

    public FriendsInfo() {
    }

    public FriendsInfo(int friendCount, int lastMonthCommission) {
        this.friendCount = friendCount;
        this.lastMonthCommission = lastMonthCommission;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(int friendCount) {
        this.friendCount = friendCount;
    }

    public int getLastMonthCommission() {
        return lastMonthCommission;
    }

    public void setLastMonthCommission(int lastMonthCommission) {
        this.lastMonthCommission = lastMonthCommission;
    }

    @Override
    public String toString() {
        return "FriendsInfo{" +
                "friendCount=" + friendCount +
                ", lastMonthCommission=" + lastMonthCommission +
                '}';
    }
}
