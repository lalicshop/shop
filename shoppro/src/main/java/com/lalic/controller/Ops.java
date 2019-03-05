package com.lalic.controller;

import com.lalic.entity.OrderModelExt;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.ReqConfirmOrder;
import com.lalic.model.body.ReqConfirmRet;
import com.lalic.model.body.ReqDeliverOrder;
import com.lalic.service.AddressService;
import com.lalic.service.OrderService;
import com.lalic.service.ReturnService;

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

    @Autowired
    ReturnService returnService;

    @Autowired
    AddressService addressService;


    //单用户所有订单
    @RequestMapping(value = "/orderbyphone/{phone}", method = RequestMethod.GET)
    public BaseResponse orderByUserId(@PathVariable String phone) {
        String userid = addressService.getAddressByPhone(phone);
        if (userid != null) {
            return orderService.getOrderDetailByUserid(userid);
        }
        return new BaseResponse().setMess("未查询到有效数据").setCode(400);
    }

    //根据订单id查询单个订单
    @RequestMapping(value = "/orderbyorderid/{orderid}", method = RequestMethod.GET)
    public BaseResponse OrderByOrderId(@PathVariable String orderid) {
        OrderModelExt orderById = orderService.getOrderdetailById(orderid);
        return new BaseResponse().setData(orderById);
    }

    //待发货订单
    @RequestMapping(value = "/ordernotdeliver", method = RequestMethod.GET)
    public BaseResponse notdeliver() {
        return orderService.opsNotDeliver();
    }

    //发货
    @RequestMapping(value = "/deliverorder", method = RequestMethod.POST)
    public BaseResponse deliverOrder(@RequestBody ReqDeliverOrder order) {
        return orderService.deliverOrder(order);
    }

    //帮助用户确认收货
    @RequestMapping(value = "/confirmorder", method = RequestMethod.POST)
    public BaseResponse confirmOrder(@RequestBody ReqConfirmOrder confirmOrder) {
        return orderService.confirmOrder(confirmOrder);
    }

    //根据物流单号查order
    @RequestMapping(value = "/orderbydeliverno/{deliverno}", method = RequestMethod.GET)
    public BaseResponse orderByDeliverNo(@PathVariable String deliverno) {
        return orderService.opsOrderByDeliverNo(deliverno);
    }


    //确认返还已收货
    @RequestMapping(value = "/confirmreturn", method = RequestMethod.POST)
    public BaseResponse confirmReturn(@RequestBody ReqConfirmRet confirmRet) {
        return returnService.confirmReturn(confirmRet);
    }


    //所有待收货订单
    @RequestMapping(value = "/waitconfirmorder", method = RequestMethod.GET)
    public BaseResponse waitConfirmOrder() {
        return orderService.getWaitConfirmOrder();
    }


    //根据物流单号查正在归还的item
    @RequestMapping(value = "/returning/{deliverno}", method = RequestMethod.GET)
    public BaseResponse returning(@PathVariable String deliverno) {
        return returnService.returning(deliverno);
    }


    //正在归还的列表
    @RequestMapping(value = "/returnings", method = RequestMethod.GET)
    public BaseResponse returnings() {
        return returnService.returnings();
    }


    //修改订单信息

    //查询特定时间段的所有订单
}
