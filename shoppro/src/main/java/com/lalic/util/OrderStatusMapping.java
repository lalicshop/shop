package com.lalic.util;

import java.util.Properties;

public class OrderStatusMapping {


    private static Properties mapping = new Properties();

    static {
        mapping.setProperty(Constant.ORDER_STATUS_ALL,"全部");
        mapping.setProperty(Constant.ORDER_STATUS_WAITFORPAY,"等待支付");
        mapping.setProperty(Constant.ORDER_STATUS_PAID,"待发货");
        mapping.setProperty(Constant.ORDER_STATUS_DELIVERING,"待收货");
        mapping.setProperty(Constant.ORDER_STATUS_DELIVERED,"已完成");
        mapping.setProperty(Constant.ORDER_STATUS_RETURNING,"正在归还");
        mapping.setProperty(Constant.ORDER_STATUS_RETURNED,"已归还");
    }

    public static String getStatus(String id) {
        return mapping.getProperty(id) == null ? "" : mapping.getProperty(id);
    }
}
