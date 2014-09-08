package com.jk.makemoney.services;

import android.util.Log;
import com.jk.makemoney.beans.Account;
import com.jk.makemoney.utils.Constants;
import com.jk.makemoney.utils.MkHttp;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author chris.xue
 *         账户service层
 */
public class AccountService {
    private static final String TAG = "AccountService";
    private static final String ACCOUNT_BASE = Constants.API_HOST + "/account";
    private static final String INFO_ENDPOINT = ACCOUNT_BASE + "/info";
    private static final String REG_ENDPOINT = ACCOUNT_BASE + "/reg";

    /**
     * 根据用户ID获取账户信息
     *
     * @param userId
     * @return
     */
    public Account readAccountById(String userId) throws Exception {
        HttpGet get = new HttpGet(INFO_ENDPOINT + "?uid=" + userId);
        SecurityService.appendAuthHeader(get, null);
        try {
            HttpResponse response = MkHttp.getInstance().send(get).get(1, TimeUnit.MINUTES);
            String data = EntityUtils.toString(response.getEntity());
            JSONObject result = new JSONObject(data);
            if (result.getBoolean("result")) {
                return new Account(result.getJSONObject("data"));
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.e(TAG, "read account failed", e);
            if (e instanceof TimeoutException || e instanceof ExecutionException) {
                throw e;
            }
        }
        return null;
    }

    /**
     * 登录
     */
    public String register(String ihash) throws Exception {
        HttpPost post = new HttpPost(REG_ENDPOINT);
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>(2);
            params.add(new BasicNameValuePair("i_hash", ihash));
            params.add(new BasicNameValuePair("user_id", String.valueOf(Constants.USER_ID)));
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(params, "utf-8");
            post.setEntity(encodedFormEntity);
            HttpResponse response = MkHttp.getInstance().send(post).get(1, TimeUnit.MINUTES);
            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            String data = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject idJson = new JSONObject(data);
            //返回id。
            if (idJson.getBoolean("result")) {
                return idJson.getString("data");
            } else {
                Log.e("AccountService", idJson.getString("data"));
            }
            return "";
        } catch (Exception e) {
            if (e instanceof TimeoutException || e instanceof ExecutionException) {
                throw e;
            }
            Log.e(TAG, "register failed", e);
        }
        return null;
    }


}
