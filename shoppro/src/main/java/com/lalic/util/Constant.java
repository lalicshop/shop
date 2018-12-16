package com.lalic.util;

import com.lalic.wx.WXConstant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constant {

    public static final String PAY_WX = "1";

    public static final String PAY_ZFB = "2";

    public static final String ORDER_STATUS_ALL = "0";

    public static final String ORDER_STATUS_WAITFORPAY = "1";

    public static final String ORDER_STATUS_PAID = "2";

    public static final String ORDER_STATUS_DELIVERING = "3";

    public static final String ORDER_STATUS_DELIVERED = "4";

    public static final String ORDER_STATUS_RETURNING = "5";

    public static final String ORDER_STATUS_RETURNED = "6";

    public static final String ORDER_STATUS_CANCELED = "9";

    public static final String RENT = "1";

    public static final String BUY = "2";

    public static final String IS_RETURN_MONEY_NO = "0";

    public static final String IS_RETURN_MONEY_YES = "1";


    private static Properties mapping = new Properties();

    static {
        InputStream resourceAsStream = WXConstant.class.getClassLoader().getResourceAsStream("wx.properties");
        try {
            mapping.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLocalHttp() {
        if (mapping.getProperty("localhttp") == null) return "";
        return mapping.getProperty("localhttp");
    }


}
