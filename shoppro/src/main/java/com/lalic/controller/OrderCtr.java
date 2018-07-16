package com.lalic.controller;

import com.lalic.entity.OrderModel;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.DeliverResp;
import com.lalic.model.body.ReqConfirmOrder;
import com.lalic.model.body.ReqMakeOrder;
import com.lalic.model.body.ReqOrder;
import com.lalic.model.body.ReturnableResp;
import com.lalic.service.OrderService;
import com.lalic.util.UserChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderCtr {

    @Autowired
    OrderService orderService;

    @Autowired
    UserChecker userChecker;

    @RequestMapping(value = "/returnable", method = RequestMethod.POST)
    public BaseResponse getOrderDetailList(@RequestBody ReqOrder reqOrder) {
        BaseResponse response = new BaseResponse();
        String userid = reqOrder.getUserid();
        ReturnableResp orders = orderService.getOrdersReturnableOrder(userid);
        return response.setData(orders);
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public BaseResponse getOrderList(@RequestBody ReqOrder reqOrder) {
        BaseResponse response = new BaseResponse();
        response.setData(orderService.getOrderByUserid(reqOrder.getUserid()));
        return response;
    }

    @RequestMapping(value = "/deliver/{orderid}", method = RequestMethod.POST)
    public BaseResponse getOrderList(@RequestBody ReqOrder reqOrder, @PathVariable String orderid) {
        BaseResponse response = new BaseResponse();
        OrderModel order = orderService.getOrderById(orderid);
        if (!order.getUserid().equals(reqOrder.getUserid())) {
            response.setCode(403);
            response.setMess("非法查询");
            return response;
        }
        DeliverResp orderDeliver = orderService.getDeliverInfoByOrderId(orderid);

        response.setData(orderDeliver);
        return response;
    }

    @RequestMapping(value = "/makeorder", method = RequestMethod.POST)
    public BaseResponse makeOrder(@RequestBody ReqMakeOrder makeOrder) {
        BaseResponse response = new BaseResponse();
        if (!userChecker.isUserLegal(makeOrder.getUserid())) {
            response.setCode(400);
            response.setMess("非法用户");
            return response;
        }
        orderService.makeOrder(makeOrder);
        return response;
    }

    @RequestMapping(value = "/confirmcorder", method = RequestMethod.POST)
    public BaseResponse confirmOrder(@RequestBody ReqConfirmOrder confirmOrder) {
        return orderService.confirmOrder(confirmOrder);
    }

}
