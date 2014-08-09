package com.jk.makemoney.services;

import com.jk.makemoney.com.jk.makemoney.utils.UserProfile;
import com.tencent.weibo.sdk.android.component.sso.tools.MD5Tools;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.Map;

/**
 * @author chris.xue
 */
public class SecurityService {

    /**
     * @param request
     * @param params
     */
    public static void appendAuthHeader(HttpUriRequest request, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(UserProfile.getInstance().getToken());
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey()).append(entry.getValue());
            }
        }
        request.setHeader("sign", MD5Tools.toMD5(sb.toString()));
        request.setHeader("bs", sb.toString());
        request.setHeader("id", UserProfile.getInstance().getUserId());
    }

}
