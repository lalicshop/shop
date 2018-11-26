package com.lalic.controller;

import com.lalic.model.BaseResponse;
import com.lalic.service.WXService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wx")
public class WXCtr {

    @Autowired
    WXService service;

    @RequestMapping(value = "/notifyurl")
    public String notifyurl() {
        System.out.println("okokok");
        return "ok";
    }

    @RequestMapping(value = "/requstusercode/{code}",method = RequestMethod.GET)
    public BaseResponse getCode(@PathVariable String code) {
        BaseResponse response = new BaseResponse();
        String openid = service.getOpenid(code);
        if("".equals(openid))
        {
            response.setCode(500);
            response.setMess("获取openid错误");
        }else {
            response.setData(openid);
        }
        return response;
    }
}
