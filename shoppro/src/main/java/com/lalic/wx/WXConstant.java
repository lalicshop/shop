package com.lalic.wx;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WXConstant {

    public static final String PAY_SUCCESS = "SUCCESS";

    public static final String PAY_REFUND = "REFUND";

    public static final String PAY_NOTPAY = "NOTPAY";

    public static final String PAY_CLOSED = "CLOSED";

    public static final String PAY_REVOKED = "REVOKED";

    public static final String PAY_USERPAYING = "USERPAYING";

    public static final String PAY_PAYERROR = "PAYERROR";


    private static Properties mapping = new Properties();

    static {
        InputStream resourceAsStream = WXConstant.class.getClassLoader().getResourceAsStream("wx.properties");
        try {
            mapping.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAppid() {
        return mapping.getProperty("appid");
    }

    public static String getMch_id() {
        return mapping.getProperty("mch_id");
    }

    public static String getMakeorderurl() {
        return mapping.getProperty("makeorderurl");
    }

    public static String getKey() {
        return mapping.getProperty("key");
    }

    public static String getOpenIdURL() {
        return mapping.getProperty("getopenid");
    }

    public static String getLocalIP() {
        return mapping.getProperty("localIP");
    }

    public static String getQueryorderurl() {
        return mapping.getProperty("queryorder");
    }

}
