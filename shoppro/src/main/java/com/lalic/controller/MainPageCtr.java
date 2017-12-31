package com.lalic.controller;

import com.lalic.model.BaseResponse;
import com.lalic.service.JingXuanService;
import com.lalic.service.LunBoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mainpage")
public class MainPageCtr {

    @Autowired
    LunBoService lunBoService;

    @Autowired
    JingXuanService jingXuanService;

    @RequestMapping(value = "/lunbo")
    public BaseResponse getLunBo() {
        BaseResponse response = new BaseResponse();
        response.setData(lunBoService.getLunBo());
        return response;
    }

    @RequestMapping(value = "/jingxuan")
    public BaseResponse getJingXuan() {
        BaseResponse response = new BaseResponse();
        response.setData(jingXuanService.getJingXuan());
        return response;
    }


}
