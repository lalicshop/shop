package com.lalic.service;

import com.lalic.entity.Order;
import com.lalic.entity.OrderModel;
import com.lalic.model.body.AllOrderResp;
import com.lalic.model.body.DeliverResp;
import com.lalic.model.body.ReqMakeOrder;

import java.util.List;


public interface OrderService {

    OrderModel getOrderById(String orderid);

    List<Order> getOrderDetailByUserid(String userid);

    AllOrderResp getOrderByUserid(String userid);

    void makeOrder(List<ReqMakeOrder.OrderDetail> detail,String buyOrRent);

    DeliverResp getDeliverInfoByOrderId(String orderId);

    String getStatusByOrderId(String orderId);

}
