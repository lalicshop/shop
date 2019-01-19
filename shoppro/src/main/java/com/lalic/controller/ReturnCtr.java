package com.lalic.controller;

import com.lalic.model.BaseResponse;
import com.lalic.model.body.ReqMakeRet;
import com.lalic.model.body.ReqOrder;
import com.lalic.model.body.ReturnableResp;
import com.lalic.service.OrderService;
import com.lalic.service.ReturnService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/return")
public class ReturnCtr {

    @Autowired
    OrderService orderService;

    @Autowired
    ReturnService returnService;

    @RequestMapping(value = "/returnable", method = RequestMethod.POST)
    public BaseResponse getReturnable(@RequestBody ReqOrder reqOrder) {
        BaseResponse response = new BaseResponse();
        String userid = reqOrder.getUserid();
        ReturnableResp orders = orderService.getOrdersReturnableOrder(userid);
        return response.setData(orders);
    }

    @RequestMapping(value = "/returnable/item", method = RequestMethod.POST)
    public BaseResponse getReturnableItem(@RequestBody ReqOrder reqOrder) {
        BaseResponse response = new BaseResponse();
        String userid = reqOrder.getUserid();
        ReturnableResp.Inner oneReturn = orderService.getOneReturn(userid, reqOrder.getOrderid());
        return response.setData(oneReturn);
    }


    @RequestMapping(value = "/makereturn", method = RequestMethod.POST)
    public BaseResponse makeReturn(@RequestBody ReqMakeRet makeRet) {
        return returnService.makereturn(makeRet);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public BaseResponse getMakeReturn(@RequestBody ReqOrder reqOrder) {
        BaseResponse response = new BaseResponse();
        response.setData(returnService.getAll(reqOrder));
        return response;
    }

    @RequestMapping(value = "/item/{retid}", method = RequestMethod.POST)
    public BaseResponse getMakeReturn(@RequestBody ReqOrder reqOrder,@PathVariable String retid) {
        return returnService.getItem(reqOrder,retid);
    }




}
