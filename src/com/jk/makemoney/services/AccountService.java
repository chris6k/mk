package com.jk.makemoney.services;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.jk.makemoney.beans.Account;
import com.jk.makemoney.com.jk.makemoney.utils.Constants;
import com.jk.makemoney.com.jk.makemoney.utils.MkHttp;
import com.tencent.weibo.sdk.android.component.sso.tools.MD5Tools;
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
    public Account readAccountById(int userId) throws Exception {
        HttpGet get = new HttpGet(INFO_ENDPOINT + "?uid=" + userId);
        try {
            HttpResponse response = MkHttp.getInstance().send(get).get(1000, TimeUnit.MILLISECONDS);
            String data = response.getEntity().toString();
            JSONObject accountInfo = new JSONObject(data);
            return new Account(accountInfo);
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
    public String register(Context context) throws Exception {
        HttpPost post = new HttpPost(REG_ENDPOINT);
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String deviceId = telephonyManager.getDeviceId();
            List<NameValuePair> params = new ArrayList<NameValuePair>(2);
            params.add(new BasicNameValuePair("i_hash", MD5Tools.getMD5String(deviceId.getBytes())));
            params.add(new BasicNameValuePair("user_id", String.valueOf(Constants.USER_ID)));
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(params, "utf-8");
            post.setEntity(encodedFormEntity);
            HttpResponse response = MkHttp.getInstance().send(post).get(1000, TimeUnit.MILLISECONDS);
            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            String data = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject idJson = new JSONObject(data);
            //返回一个含有登录信息和要求的token。后续请求需使用该token来完成。
            return idJson.getString("token");
        } catch (Exception e) {
            if (e instanceof TimeoutException || e instanceof ExecutionException) {
                throw e;
            }
            Log.e(TAG, "register failed", e);
        }
        return null;
    }


}
