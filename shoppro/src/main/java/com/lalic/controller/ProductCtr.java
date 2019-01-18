package com.lalic.controller;

import com.lalic.model.BaseResponse;
import com.lalic.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductCtr {
    private static final Logger logger = LoggerFactory.getLogger(AddressCtr.class);

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/productid/{productid}")
    public BaseResponse getJingXuan(@PathVariable String productid) {
        logger.info("/product/productid/{productid} : "+productid);
        return productService.getProductById(productid);
    }

    @RequestMapping(value = "/fleshpart/fleshpartid/{fleshpartid}")
    public BaseResponse getByFleshPart(@PathVariable String fleshpartid) {
        logger.info("/product/fleshpart/fleshpartid/{fleshpartid} : "+fleshpartid);
        BaseResponse response = new BaseResponse();
        response.setData(productService.getProductByFleshPartId(fleshpartid));
        return response;
    }

    @RequestMapping(value = "/fleshpart/fleshpartid")
    public BaseResponse getFleshPartID() {
        logger.info("/product/fleshpart/fleshpartid");
        BaseResponse response = new BaseResponse();
        response.setData(productService.getFleshPartID());
        return response;
    }




}
