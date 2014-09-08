package com.jk.makemoney.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * @author chris.xue
 */
public interface HttpCallback {
    void onComplete(HttpResponse response, HttpUriRequest request);

    void onError(Throwable throwable);

}
