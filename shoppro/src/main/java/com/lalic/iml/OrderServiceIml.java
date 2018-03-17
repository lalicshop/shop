package com.lalic.iml;

import com.lalic.dao.OrderDao;
import com.lalic.dao.OrderDetailDao;
import com.lalic.entity.Order;
import com.lalic.entity.OrderDetailModel;
import com.lalic.entity.OrderModel;
import com.lalic.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceIml implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderDetailDao orderDetailDao;

    @Override
    public OrderModel getOrderById(String orderid) {
        return orderDao.getOrderById(orderid);
    }

    @Override
    public List<Order> getOrderByUserid(String userid) {
        List<OrderModel> orderByUserid = orderDao.getOrderByUserid(userid);
        List<Order> orders = new ArrayList<>();
        orderByUserid.forEach(order -> {
            List<OrderDetailModel> orderDetailByOrderid = orderDetailDao.getOrderDetailByOrderid(order.getOrderid());
            orders.add(new Order(order, orderDetailByOrderid));
        });
        return orders;
    }
}
