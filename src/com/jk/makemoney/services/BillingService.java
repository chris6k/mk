package com.jk.makemoney.services;

import android.util.Log;
import com.jk.makemoney.beans.UserBilling;
import com.jk.makemoney.utils.Constants;
import com.jk.makemoney.utils.MkHttp;
import com.jk.makemoney.utils.UserProfile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author chris.xue
 */
public class BillingService {
    private static final String TAG = "BillingService";
    private static final String BILLING_BASE = Constants.API_HOST + "/billing";
    private static final String BILLING_LIST_ENDPOINT = BILLING_BASE + "/list";

    /**
     * @param first
     * @param count
     * @return
     */
    public List<UserBilling> getBilling(int first, int count) throws Exception {
        List<UserBilling> result = new ArrayList<UserBilling>(count);
        HttpGet get = new HttpGet(BILLING_LIST_ENDPOINT + "?uid=" + UserProfile.getInstance().getUserId() + "&idx=" + first + "&total=" + count);
        SecurityService.appendAuthHeader(get, null);
        try {
            HttpResponse response = MkHttp.getInstance().send(get).get(1, TimeUnit.MINUTES);
            if (response.getStatusLine().getStatusCode() != 200) {
                return result;
            }
            String data = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject resp = new JSONObject(data);
            if (resp.getBoolean("result")) {
                JSONArray array = resp.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    try {
                        result.add(new UserBilling(array.getJSONObject(i)));
                    } catch (JSONException e) {
                        Log.e(TAG, "parse json object err", e);
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "get billing list failed", e);
//            if (e instanceof TimeoutException || e instanceof ExecutionException) {
//                throw e;
//            }
        }
        return result;
    }
}
