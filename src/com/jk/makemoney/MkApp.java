package com.jk.makemoney;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.jk.makemoney.com.jk.makemoney.utils.UserProfile;
import com.jk.makemoney.services.AccountService;
import com.tencent.weibo.sdk.android.component.sso.tools.MD5Tools;

/**
 * @author kun
 */
public class MkApp extends Application {
    private static Context context;
    private AccountService accountService;

    @Override
    public void onCreate() {
        MkApp.context = getApplicationContext();
        accountService = new AccountService();
//        UserProfile.getInstance().setUserId("");
        super.onCreate();
        //用户ID为空则注册用户
        String userId = UserProfile.getInstance().getUserId();
        String token = UserProfile.getInstance().getToken();
        if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(token)) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                String ihash = MD5Tools.getMD5String(telephonyManager.getDeviceId().getBytes());
                userId = accountService.register(ihash);
                UserProfile.getInstance().setUserId(userId);
                UserProfile.getInstance().setToken(userId + "|" + ihash);
            } catch (Exception e) {
                Log.e("MkApp", "reg user failed", e);
            }
        }
    }

    public static Context getContext() {
        return context;
    }
}
