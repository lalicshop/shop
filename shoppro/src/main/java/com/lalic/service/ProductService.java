package com.lalic.service;

import com.lalic.entity.ProductModel;

import java.util.List;


public interface ProductService {

    List<ProductModel> getAllProducts();

    ProductModel getProductById(String id);
}
