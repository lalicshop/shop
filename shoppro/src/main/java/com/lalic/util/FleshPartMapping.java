package com.lalic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FleshPartMapping {


    private static Properties mapping = new Properties();

    static {
        InputStream resourceAsStream = FleshPartMapping.class.getClassLoader().getResourceAsStream("fleshpart.properties");
        try {
            mapping.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFleshPartNameById(String id) {
        return mapping.getProperty(id) == null ? "" : mapping.getProperty(id);
    }
}
