package com.jk.makemoney.utils;

import java.text.DecimalFormat;

/**
 * @author chris.xue
 */
public class NumberUtils {
    private static final DecimalFormat format = new DecimalFormat("#,##0.00");

    public static String formatFloat(float num) {
        return format.format(num);
    }
}
