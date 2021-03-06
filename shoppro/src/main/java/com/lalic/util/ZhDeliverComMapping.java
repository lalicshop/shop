package com.lalic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ZhDeliverComMapping {


    private static Properties mapping = new Properties();

    static {
        InputStream resourceAsStream = FleshPartMapping.class.getClassLoader().getResourceAsStream("zhdelivercom.properties");
        try {
            mapping.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDeliverCom(String id) {
        return mapping.getProperty(id) == null ? "" : mapping.getProperty(id);
    }

}
