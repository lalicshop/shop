package com.lalic.controller;

import com.lalic.model.BaseResponse;
import com.lalic.service.OrderService;
import com.lalic.service.WXService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/wx")
public class WXCtr {

    private static final Logger logger = LoggerFactory.getLogger(WXCtr.class);

    @Autowired
    WXService service;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/notifyurl/{orderid}", method = RequestMethod.GET)
    public BaseResponse notifyurl(@PathVariable String orderid) {
        logger.info("wx/notifyurl/{orderid}: " + orderid);
//        WXPayResResp wxPayResResp = WXPay.searchPayRes(orderid);
//        if (WXConstant.PAY_SUCCESS.equals(wxPayResResp.getTrade_state())) {

//        }
        return orderService.confirmPay(orderid);
    }

    @RequestMapping(value = "/requstusercode/{code}", method = RequestMethod.GET)
    public BaseResponse getCode(@PathVariable String code, HttpServletRequest request) {
        logger.info("requstusercode/" + code+" "+request.getRemoteAddr());
        BaseResponse response = new BaseResponse();
        String openid = service.getOpenid(code);
        if ("".equals(openid)) {
            response.setCode(500);
            response.setMess("获取openid错误");
        } else {
            response.setData(openid);
        }
        return response;
    }
}
