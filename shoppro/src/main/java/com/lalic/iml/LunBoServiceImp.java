package com.lalic.iml;

import com.lalic.dao.LunBoDao;
import com.lalic.entity.LunBoModel;
import com.lalic.service.LunBoService;
import com.lalic.util.Constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LunBoServiceImp implements LunBoService {

    @Autowired
    LunBoDao lunBoDao;

    @Override
    public List<LunBoModel> getLunBo() {
        List<LunBoModel> lunBo = lunBoDao.getLunBo();
        for (LunBoModel lunBoModel : lunBo) {
            lunBoModel.setLunboimgurl(Constant.getLocalHttp() + lunBoModel.getLunboimgurl());
        }
        return lunBo;
    }
}
