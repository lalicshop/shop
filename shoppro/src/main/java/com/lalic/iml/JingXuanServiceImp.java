package com.lalic.iml;

import com.lalic.dao.ProductDao;
import com.lalic.entity.ProductModel;
import com.lalic.service.JingXuanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JingXuanServiceImp implements JingXuanService {

    @Autowired
    ProductDao productDao;

    @Override
    public List<ProductModel> getJingXuan() {
        return productDao.getJingXuan();
    }

}
