package com.lalic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DeliverPriceMapping {


    private static Properties mapping = new Properties();

    static {
        InputStream resourceAsStream = DeliverPriceMapping.class.getClassLoader().getResourceAsStream("deliverprice.properties");
        try {
            mapping.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getPrice(String price) {
        return Integer.parseInt(mapping.getProperty(price) == null ? "10" : mapping.getProperty(price));
    }
}
