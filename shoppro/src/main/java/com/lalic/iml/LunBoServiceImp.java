package com.lalic.iml;

import com.lalic.dao.LunBoDao;
import com.lalic.entity.LunBoModel;
import com.lalic.service.LunBoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LunBoServiceImp implements LunBoService {

    @Autowired
    LunBoDao lunBoDao;

    @Override
    public List<LunBoModel> getLunBo() {
        return lunBoDao.getLunBo();
    }
}
