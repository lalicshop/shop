package com.lalic.controller;

import com.lalic.entity.JingXuan;
import com.lalic.entity.LunBoModel;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.MainPageResp;
import com.lalic.service.JingXuanService;
import com.lalic.service.LunBoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/mainpage")
public class MainPageCtr {

    @Autowired
    LunBoService lunBoService;

    @Autowired
    JingXuanService jingXuanService;

//    @RequestMapping(value = "/lunbo")
//    public BaseResponse getLunBo() {
//        BaseResponse response = new BaseResponse();
//        response.setData(lunBoService.getLunBo());
//        return response;
//    }
//
//    @RequestMapping(value = "/jingxuan")
//    public BaseResponse getJingXuan() {
//        BaseResponse response = new BaseResponse();
//        response.setData(jingXuanService.getJingXuan());
//        return response;
//    }

    @RequestMapping(value = "/main")
    public BaseResponse getMainPage() {
        BaseResponse response = new BaseResponse();
        List<LunBoModel> lunBo = lunBoService.getLunBo();
        List<JingXuan> jingXuan = jingXuanService.getJingXuan();
        MainPageResp mpr = new MainPageResp();
        mpr.setSlide_datas(lunBo);
        mpr.setMain_details(jingXuan);
        response.setData(mpr);
        return response;
    }


}
