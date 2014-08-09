package com.jk.makemoney.services;

import android.util.Log;
import com.jk.makemoney.beans.FriendsInfo;
import com.jk.makemoney.com.jk.makemoney.utils.Constants;
import com.jk.makemoney.com.jk.makemoney.utils.MkHttp;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
    private FriendsInfo readInfoById(int userId) throws Exception {
        HttpGet get = new HttpGet(INFO_ENDPOINT + "?uid=" + userId);
        SecurityService.appendAuthHeader(get, null);
        try {
            HttpResponse response = MkHttp.getInstance().send(get).get(1000, TimeUnit.MILLISECONDS);
            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            String data = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject friendInfo = new JSONObject(data);
            return new FriendsInfo(friendInfo);
        } catch (Exception e) {
            Log.e(TAG, "read friends info by userId[" + userId + "] failed", e);
            if (e instanceof TimeoutException || e instanceof ExecutionException) {
                throw e;
            }
            return null;
        }
    }

}
