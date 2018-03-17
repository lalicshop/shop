package com.lalic.service;

import com.lalic.entity.Order;
import com.lalic.entity.OrderModel;

import java.util.List;


public interface OrderService {

    OrderModel getOrderById(String orderid);

    List<Order> getOrderByUserid(String userid);

}
