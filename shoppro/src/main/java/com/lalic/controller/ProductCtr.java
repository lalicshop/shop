package com.lalic.controller;

import com.lalic.model.BaseResponse;
import com.lalic.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductCtr {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/productid/{productid}")
    public BaseResponse getJingXuan(@PathVariable String productid) {
        BaseResponse response = new BaseResponse();
        response.setData(productService.getProductById(productid));
        return response;
    }

    @RequestMapping(value = "/fleshpart/fleshpartid/{fleshpartid}")
    public BaseResponse getByFleshPart(@PathVariable String fleshpartid) {
        BaseResponse response = new BaseResponse();
        response.setData(productService.getProductByFleshPartId(fleshpartid));
        return response;
    }

    @RequestMapping(value = "/fleshpart/fleshpartid")
    public BaseResponse getFleshPartID() {
        BaseResponse response = new BaseResponse();
        response.setData(productService.getFleshPartID());
        return response;
    }




}
