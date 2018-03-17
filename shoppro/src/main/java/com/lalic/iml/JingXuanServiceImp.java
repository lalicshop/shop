package com.lalic.iml;

import com.lalic.dao.JingXuanDao;
import com.lalic.entity.JingXuan;
import com.lalic.service.JingXuanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JingXuanServiceImp implements JingXuanService {

    @Autowired
    JingXuanDao jingXuanDao;

    @Override
    public List<JingXuan> getJingXuan() {
        return jingXuanDao.getJingXuan();
    }

}
