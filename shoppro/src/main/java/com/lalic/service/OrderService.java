package com.lalic.service;

import com.lalic.entity.OrderModel;
import com.lalic.entity.OrderModelExt;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.AllOrderResp;
import com.lalic.model.body.DeliverResp;
import com.lalic.model.body.ReqConfirmOrder;
import com.lalic.model.body.ReqDeliverOrder;
import com.lalic.model.body.ReqMakeOrder;
import com.lalic.model.body.ReqMakeWaitOrder;
import com.lalic.model.body.ReqRemoveOrder;
import com.lalic.model.body.ReturnableResp;


public interface OrderService {

    OrderModel getOrderById(String orderid);

    AllOrderResp getOrderByUserid(String userid);

    BaseResponse makeOrder(ReqMakeOrder makeOrder) throws Exception;

    BaseResponse makeWaitOrder(ReqMakeWaitOrder makeOrder) throws Exception;

    DeliverResp getDeliverInfoByOrderId(String orderId);

    ReturnableResp getOrdersReturnableOrder(String userid);

    BaseResponse confirmOrder(ReqConfirmOrder orderId);

    BaseResponse deliverOrder(ReqDeliverOrder orderid);

    BaseResponse removeOrder(ReqRemoveOrder removeOrder);

    ReturnableResp.Inner getOneReturn(String userid, String orderid);

    BaseResponse confirmPay(String orderid);

    BaseResponse opsNotDeliver();

    BaseResponse opsOrderByDeliverNo(String deliverid);

    BaseResponse getWaitConfirmOrder();

    BaseResponse getOrderDetailByUserid(String userid);

    OrderModelExt getOrderdetailById(String orderid);
}
