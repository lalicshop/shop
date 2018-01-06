package com.lalic.iml;

import com.lalic.dao.ProductDao;
import com.lalic.entity.ProductModel;
import com.lalic.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceIml implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public List<ProductModel> getAllProducts() {
        return productDao.getAllProduct();
    }
}
