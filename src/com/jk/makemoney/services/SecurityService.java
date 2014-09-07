package com.jk.makemoney.services;

import com.jk.makemoney.com.jk.makemoney.utils.UserProfile;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
        request.setHeader("mk_token", UserProfile.getInstance().getToken());
        request.setHeader("mk_id", UserProfile.getInstance().getUserId());
    }

    private static String encode(String base) {
        try {
            return URLEncoder.encode(base, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return base;
        }
    }

}
