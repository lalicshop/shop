package com.lalic.service;

import com.lalic.entity.ProductModel;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.FleshPartIDResp;

import java.util.List;


public interface ProductService {

    List<ProductModel> getAllProducts();

    BaseResponse getProductById(String productid);

    List<ProductModel> getProductByFleshPartId(String fleshpartid);

    List<FleshPartIDResp> getFleshPartID();
}
