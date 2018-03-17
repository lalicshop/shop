package com.lalic.controller;

import com.lalic.model.BaseResponse;
import com.lalic.model.reqbody.ReqOrder;
import com.lalic.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderCtr {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BaseResponse getOrderList(@RequestBody ReqOrder reqOrder) {
        BaseResponse response = new BaseResponse();
        response.setData(orderService.getOrderByUserid(reqOrder.getUserid()));
        return response;
    }

}
