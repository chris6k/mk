package com.jk.makemoney.beans;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * @author chris.xue
 *         排位信息
 */
public class RankInfo implements Comparable<RankInfo>, Serializable {
    private static final long serialVersionUID = 163164563933826686L;
    //记录顺序号
    private int id;
    //好友总数
    private int friendCount;
    //上月好友分成
    private int lastMonthfriendCommission;

    public RankInfo(int id, int friendCount, int lastMonthfriendCommission) {
        this.id = id;
        this.friendCount = friendCount;
        this.lastMonthfriendCommission = lastMonthfriendCommission;
    }

    public RankInfo() {
    }

    public int getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(int friendCount) {
        this.friendCount = friendCount;
    }

    public int getLastMonthfriendCommission() {
        return lastMonthfriendCommission;
    }

    public void setLastMonthfriendCommission(int lastMonthfriendCommission) {
        this.lastMonthfriendCommission = lastMonthfriendCommission;
    }

    @Override
    public String toString() {
        return "RankInfo{" +
                "id=" + id +
                ", friendCount=" + friendCount +
                ", lastMonthfriendCommission=" + lastMonthfriendCommission +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RankInfo rankInfo = (RankInfo) o;

        if (id != rankInfo.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int compareTo(@NonNull RankInfo rankInfo) {
        return rankInfo.id == id ? 0 : (rankInfo.id > id ? -1 : 1);
    }
}
