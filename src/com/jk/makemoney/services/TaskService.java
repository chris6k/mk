package com.jk.makemoney.services;

import android.util.Log;
import com.jk.makemoney.com.jk.makemoney.utils.Constants;
import com.jk.makemoney.com.jk.makemoney.utils.MkHttp;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author chris.xue
 *         任务service层
 */
public class TaskService {
    private static final String TAG = "TaskService";
    private static final String TASK_BASE = Constants.API_HOST + "/task";
    private static final String FINISH_ENDPOINT = TASK_BASE + "/finish";

    /**
     * 任务完成
     *
     * @param platformId
     * @param taskId
     * @param amount
     */
    public void finishTask(int userId, int platformId, String taskId, int amount, String taskName) {
        HttpPost post = new HttpPost(FINISH_ENDPOINT);
        try {
            List<NameValuePair> params = new ArrayList<NameValuePair>(5);
            params.add(new BasicNameValuePair("userId", String.valueOf(userId)));
            params.add(new BasicNameValuePair("platformId", String.valueOf(platformId)));
            params.add(new BasicNameValuePair("taskId", taskId));
            params.add(new BasicNameValuePair("amount", String.valueOf(amount)));
            params.add(new BasicNameValuePair("taskName", taskName));
            post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            HttpResponse response = MkHttp.getInstance().send(post).get(1000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            Log.e(TAG, "finish task failed", e);
        }
    }
}
