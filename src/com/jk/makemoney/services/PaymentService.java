package com.jk.makemoney.services;

import android.util.Log;
import com.jk.makemoney.utils.Constants;
import com.jk.makemoney.utils.MkHttp;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * @author chris.xue
 */
public class PaymentService {
    private static final String TAG = "PaymentService";
    private static final String PAYMENT_BASE = Constants.API_HOST + "/payment";
    private static final String PAYMENT_ASK_FOR_ENDPOINT = PAYMENT_BASE + "/ask";

    /**
     * ask for settlement.
     *
     * @param payment
     * @return
     */
    public String askForSettlement(String account, String payment, int paymentType) {
        HttpPost post = new HttpPost(PAYMENT_ASK_FOR_ENDPOINT);
        Map<String, String> map = new TreeMap<String, String>();
        List<NameValuePair> params = new ArrayList<NameValuePair>(1);
        params.add(new BasicNameValuePair("payment", payment));
        params.add(new BasicNameValuePair("account", account));
        params.add(new BasicNameValuePair("paymentType", String.valueOf(paymentType)));
        map.put("payment", String.valueOf(payment));
        SecurityService.appendAuthHeader(post, map);
        try {
            post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            HttpResponse response = MkHttp.getInstance().send(post).get(1, TimeUnit.MINUTES);
            if (response.getStatusLine().getStatusCode() != 200) {
                return "错误的参数";
            }
            String content = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject jsonObject = new JSONObject(content);
            if (jsonObject.has("result") && jsonObject.getBoolean("result")) {
                return null;
            } else {
                return jsonObject.has("data") ? jsonObject.getString("data") : "申请支付失败，发生未知错误";
            }
        } catch (Exception e) {
            Log.e(TAG, "ask for settlement failed", e);
        }
        return "";
    }
}
