package com.jk.makemoney.services;

import android.util.Log;
import com.jk.makemoney.com.jk.makemoney.utils.Constants;
import com.jk.makemoney.com.jk.makemoney.utils.MkHttp;
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
    public boolean askForSettlement(int payment) throws Exception {
        HttpPost post = new HttpPost(PAYMENT_ASK_FOR_ENDPOINT);
        Map<String, String> map = new TreeMap<String, String>();
        List<NameValuePair> params = new ArrayList<NameValuePair>(1);
        params.add(new BasicNameValuePair("payment", String.valueOf(payment)));
        map.put("payment", String.valueOf(payment));
        SecurityService.appendAuthHeader(post, map);
        try {
            post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            HttpResponse response = MkHttp.getInstance().send(post).get(1000, TimeUnit.MILLISECONDS);
            if (response.getStatusLine().getStatusCode() != 200) {
                return false;
            }
            String content = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject jsonObject = new JSONObject(content);
            return (jsonObject.has("result") && jsonObject.getBoolean("result"));
        } catch (Exception e) {
            Log.e(TAG, "ask for settlement failed", e);
        }
        return false;
    }

}
