package com.lalic.service;

import com.lalic.entity.ProductModel;
import com.lalic.model.body.FleshPartIDResp;
import com.lalic.model.body.ProductDetailResp;

import java.util.List;


public interface ProductService {

    List<ProductModel> getAllProducts();

    ProductDetailResp getProductById(String productid);

    List<ProductModel> getProductByFleshPartId(String fleshpartid);

    List<FleshPartIDResp> getFleshPartID();
}
