package com.lalic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DeliverComMapping {


    private static Properties mapping = new Properties();

    static {
        InputStream resourceAsStream = FleshPartMapping.class.getClassLoader().getResourceAsStream("delivercom.properties");
        try {
            mapping.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDeliverCom(String id) {
        return mapping.getProperty(id) == null ? "" : mapping.getProperty(id);
    }

    public static String getSearchUrl() {
        return mapping.getProperty("searchurl");
    }
}
