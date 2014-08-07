package com.jk.makemoney.services;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.jk.makemoney.beans.Account;
import com.jk.makemoney.com.jk.makemoney.utils.MkHttp;
import com.jk.makemoney.com.jk.makemoney.utils.Constants;
import com.tencent.weibo.sdk.android.component.sso.tools.MD5Tools;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author chris.xue
 *         账户service层
 */
public class AccountService {

    private static final String ACCOUNT_BASE = Constants.API_HOST + "/account";
    private static final String INFO_ENDPOINT = ACCOUNT_BASE + "/info";
    private static final String REG_ENDPOINT = ACCOUNT_BASE + "/reg";

    /**
     * 根据用户ID获取账户信息
     *
     * @param userId
     * @return
     */
    public Account readAccountById(int userId) {
        HttpGet get = new HttpGet(INFO_ENDPOINT + "?uid=" + userId);
        try {
            HttpResponse response = MkHttp.getInstance().send(get).get(1000, TimeUnit.MILLISECONDS);
            String data = response.getEntity().toString();
            JSONObject accountInfo = new JSONObject(data);
            return new Account(accountInfo);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 登录
     */
    public int register(Context context) {
        HttpPost post = new HttpPost(REG_ENDPOINT);
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String deviceId = telephonyManager.getDeviceId();
            List<NameValuePair> params = new ArrayList<NameValuePair>(2);
            params.add(new BasicNameValuePair("i_hash", MD5Tools.getMD5String(deviceId.getBytes())));
            //TODO
            params.add(new BasicNameValuePair("invite_id", String.valueOf(Constants.INVITE_ID)));
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(params, "utf-8");
            post.setEntity(encodedFormEntity);
            HttpResponse response = MkHttp.getInstance().send(post).get(1000, TimeUnit.MILLISECONDS);
            String data = response.getEntity().toString();
            JSONObject idJson = new JSONObject(data);
            return idJson.getInt("id");
        } catch (Exception e) {
            return 0;
        }
    }


}
