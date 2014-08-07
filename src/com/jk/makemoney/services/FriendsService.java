package com.jk.makemoney.services;

import android.util.Log;
import com.jk.makemoney.beans.FriendsInfo;
import com.jk.makemoney.com.jk.makemoney.utils.Constants;
import com.jk.makemoney.com.jk.makemoney.utils.MkHttp;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

/**
 * @author chris.xue
 *         好友service层
 */
public class FriendsService {
    private static final String TAG = "FriendsService";
    private static final String FRIEND_BASE = Constants.API_HOST + "/friends";
    private static final String INFO_ENDPOINT = FRIEND_BASE + "/info";

    /**
     * 读取好友信息通过用户ID
     *
     * @param userId
     * @return
     */
    private FriendsInfo readInfoById(int userId) {
        HttpGet get = new HttpGet(INFO_ENDPOINT + "?uid=" + userId);
        try {
            HttpResponse response = MkHttp.getInstance().send(get).get(1000, TimeUnit.MILLISECONDS);
            String data = response.getEntity().toString();
            JSONObject friendInfo = new JSONObject(data);
            return new FriendsInfo(friendInfo);
        } catch (Exception e) {
            Log.e(TAG, "read friends info by userId[" + userId + "] failed", e);
            return null;
        }
    }

}
