package com.lalic.iml;

import com.alibaba.fastjson.JSON;
import com.lalic.dao.UserDao;
import com.lalic.entity.UserModel;
import com.lalic.http.SimpleHttp;
import com.lalic.service.WXService;
import com.lalic.wx.WXConstant;
import com.lalic.wx.WXOpenIdReps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WXServiceIml implements WXService {

    @Autowired
    UserDao dao;

    @Override
    public String getOpenid(String usercode) {
        String s = SimpleHttp.get(WXConstant.getOpenIdURL() + usercode, null).getBody();
        WXOpenIdReps wxOpenIdReps = JSON.parseObject(s, WXOpenIdReps.class);
        String openid = wxOpenIdReps.getOpenid();
        if (openid == null) {
            return "";
        }
        UserModel user = new UserModel();
        user.setUserid(openid);
        dao.save(user);
        return openid;
    }
}
