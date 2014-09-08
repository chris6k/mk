package com.jk.makemoney.beans;

import android.util.Log;
import com.jk.makemoney.utils.NumberUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public FriendsInfo(JSONObject jsonObject) throws JSONException {
        friendCount = jsonObject.getInt("friendCount");
        lastMonthCommission = jsonObject.getInt("lastMonthCommission");
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

    public String getLastMonthCommissionYuan() {
        return NumberUtils.formatFloat((float) lastMonthCommission / 100);
    }

    @Override
    public String toString() {
        return "FriendsInfo{" +
                "friendCount=" + friendCount +
                ", lastMonthCommission=" + lastMonthCommission +
                '}';
    }

    public static List<FriendsInfo> parse(JSONArray rankingArray) {
        assert rankingArray != null;
        List<FriendsInfo> friendsInfos = new ArrayList<FriendsInfo>(rankingArray.length());
        for (int i = 0; i < rankingArray.length(); i++) {
            try {
                friendsInfos.add(new FriendsInfo(rankingArray.getJSONObject(i)));
            } catch (Exception e) {
                Log.e("FriendsInfo", "parse ranking list failed", e);
            }
        }
        return friendsInfos;
    }
}
