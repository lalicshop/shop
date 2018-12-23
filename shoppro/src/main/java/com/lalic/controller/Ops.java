package com.lalic.controller;

import com.lalic.entity.OrderModel;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.AllOrderResp;
import com.lalic.model.body.ReqConfirmOrder;
import com.lalic.model.body.ReqDeliverOrder;
import com.lalic.service.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ops")
public class Ops {
    private static final Logger logger = LoggerFactory.getLogger(AddressCtr.class);

    @Autowired
    OrderService orderService;


    @RequestMapping(value = "/orderbyuserid/{userid}", method = RequestMethod.GET)
    public BaseResponse orderByUserId(@PathVariable String userid) {
        AllOrderResp orderByUserid = orderService.getOrderByUserid(userid);
        return new BaseResponse().setData(orderByUserid);
    }

    @RequestMapping(value = "/orderbyorderid/{orderid}", method = RequestMethod.GET)
    public BaseResponse OrderByOrderId(@PathVariable String orderid) {
        OrderModel orderById = orderService.getOrderById(orderid);
        return new BaseResponse().setData(orderById);
    }

    @RequestMapping(value = "/ordernotdeliver/", method = RequestMethod.GET)
    public BaseResponse notdeliver() {
        return orderService.opsNotDeliver();
    }

    @RequestMapping(value = "/deliverorder", method = RequestMethod.POST)
    public BaseResponse deliverOrder(@RequestBody ReqDeliverOrder order) {
        return orderService.deliverOrder(order);
    }


    @RequestMapping(value = "/confirmcorder", method = RequestMethod.POST)
    public BaseResponse confirmOrder(@RequestBody ReqConfirmOrder confirmOrder) {
        return orderService.opsConfirmOrder(confirmOrder);
    }

}
