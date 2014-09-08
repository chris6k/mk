package com.jk.makemoney.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.jk.makemoney.MkApp;

/**
 * @author kun
 */
public class ToastUtils {
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void show(final String message) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MkApp.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
