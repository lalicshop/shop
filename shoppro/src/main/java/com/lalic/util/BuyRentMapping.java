package com.lalic.util;

import java.util.Properties;

public class BuyRentMapping {

    private static Properties mapping = new Properties();

    static {
        mapping.setProperty(Constant.BUY,"购买");
        mapping.setProperty(Constant.RENT,"租赁");
    }

    public static String getBuyRent(String id) {
        return mapping.getProperty(id) == null ? "" : mapping.getProperty(id);
    }
}
