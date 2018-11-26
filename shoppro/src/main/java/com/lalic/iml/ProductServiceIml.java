package com.lalic.iml;

import com.lalic.dao.ProductDao;
import com.lalic.entity.ProductModel;
import com.lalic.model.body.FleshPartIDResp;
import com.lalic.model.body.ProductDetailResp;
import com.lalic.service.ProductService;
import com.lalic.util.FleshPartMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductServiceIml implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public List<ProductModel> getAllProducts() {
        return productDao.getAllProduct();
    }

    @Override
    public ProductDetailResp getProductById(String id) {
        ProductModel product = productDao.getProductById(id);
        ProductDetailResp pdr = new ProductDetailResp();
        ProductDetailResp.Goods goods = new ProductDetailResp.Goods();
        ProductDetailResp.Attributes attributes = new ProductDetailResp.Attributes();
        pdr.setAttributes(attributes);
        pdr.setGoods(goods);

        goods.setId(id);
        goods.setDetail(product.getMess());
        goods.setImage(product.getMainpic());
        goods.setTitle(product.getShortname());
        goods.setParameter(product.getAttr());
        goods.setService(product.getAftersale());

        attributes.setBuy_price(product.getBuyprice());
        attributes.setRent_price(product.getRentprice());
        attributes.setGoods_dsp(product.getDetailname());
        attributes.setGoods_cont(product.getShortdescription());

        pdr.setSubImgUrls(Arrays.asList(product.getSubpic().split(";")));
        pdr.setDetailImgUrls(Arrays.asList(product.getDetailpic().split(";")));

        return pdr;
    }

    @Override
    public List<ProductModel> getProductByFleshPartId(String fleshpartid) {
        List<ProductModel> productByFleshPartID = productDao.getProductByFleshPartID(fleshpartid);
        return productByFleshPartID;
    }

    @Override
    public List<FleshPartIDResp> getFleshPartID() {
        List<String> fleshPartID = productDao.getFleshPartID();
        List<FleshPartIDResp> ret = new ArrayList<>();
        for (String id : fleshPartID) {
            FleshPartIDResp obj = new FleshPartIDResp();
            obj.setFleshPartId(id);
            obj.setFleshName(FleshPartMapping.getFleshPartNameById(id));
            ret.add(obj);
        }
        return ret;
    }
}
