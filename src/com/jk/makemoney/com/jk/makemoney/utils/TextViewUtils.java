package com.jk.makemoney.com.jk.makemoney.utils;

import android.view.View;
import android.widget.TextView;

/**
 * @author chris.xue
 *         辅助类
 */
public final class TextViewUtils {
    public static void setText(View textView, String text) {
        if (textView != null && textView instanceof TextView) {
            ((TextView) textView).setText(text);
        }
    }
}
