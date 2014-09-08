package com.jk.makemoney.services;

import android.util.Log;
import com.jk.makemoney.beans.FriendsInfo;
import com.jk.makemoney.utils.Constants;
import com.jk.makemoney.utils.MkHttp;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
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
    private static final String RANK_ENDPOINT = FRIEND_BASE + "/rank";
    public static final String INVITE_ENDPOINT = Constants.API_HOST + "/invite/";

    /**
     * 读取好友信息通过用户ID
     *
     * @param userId
     * @return
     */
    public FriendsInfo readInfoById(String userId) throws Exception {
        HttpGet get = new HttpGet(INFO_ENDPOINT + "?uid=" + userId);
        SecurityService.appendAuthHeader(get, null);
        try {
            HttpResponse response = MkHttp.getInstance().send(get).get(1000, TimeUnit.MILLISECONDS);
            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            String data = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject result = new JSONObject(data);
            if (result.getBoolean("result")) {
                return new FriendsInfo(result.getJSONObject("data"));
            }
        } catch (Exception e) {
            Log.e(TAG, "read friends info by userId[" + userId + "] failed", e);
            if (e instanceof TimeoutException || e instanceof ExecutionException) {
                throw e;
            }
        }
        return null;
    }

    public List<FriendsInfo> readRankingList() throws Exception {
        HttpGet get = new HttpGet(RANK_ENDPOINT);
        SecurityService.appendAuthHeader(get, null);
        try {
            HttpResponse response = MkHttp.getInstance().send(get).get(1000, TimeUnit.MILLISECONDS);
            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            String data = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONArray rankingArray = new JSONArray(data);
            return FriendsInfo.parse(rankingArray);
        } catch (Exception e) {
            Log.e(TAG, "read ranking list failed", e);
            if (e instanceof TimeoutException || e instanceof ExecutionException) {
                throw e;
            }
            return null;
        }
    }

}
