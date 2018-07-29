package com.lalic.service;

import com.lalic.entity.OrderModel;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.AllOrderResp;
import com.lalic.model.body.DeliverResp;
import com.lalic.model.body.ReqConfirmOrder;
import com.lalic.model.body.ReqDeliverOrder;
import com.lalic.model.body.ReqMakeOrder;
import com.lalic.model.body.ReqRemoveOrder;
import com.lalic.model.body.ReturnableResp;


public interface OrderService {

    OrderModel getOrderById(String orderid);

    AllOrderResp getOrderByUserid(String userid);

    void makeOrder(ReqMakeOrder makeOrder);

    DeliverResp getDeliverInfoByOrderId(String orderId);

    ReturnableResp getOrdersReturnableOrder(String userid);

    BaseResponse confirmOrder(ReqConfirmOrder orderId);

    BaseResponse deliverOrder(ReqDeliverOrder orderid);

    BaseResponse removeOrder(ReqRemoveOrder removeOrder);
}
