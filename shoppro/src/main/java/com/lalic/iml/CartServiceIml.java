package com.lalic.iml;

import com.lalic.dao.CartDao;
import com.lalic.dao.ProductDao;
import com.lalic.entity.CartModel;
import com.lalic.entity.ProductModel;
import com.lalic.model.body.CartResp;
import com.lalic.model.body.ReqRemoveCart;
import com.lalic.service.CartService;
import com.lalic.util.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartServiceIml implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceIml.class);

    @Autowired
    ProductDao productDao;

    @Autowired
    CartDao cartDao;


    @Override
    public CartResp getCartByUserId(String userId) {
        CartResp cp = new CartResp();
        List<CartModel> carts = cartDao.getCartByUserId(userId);

        for (CartModel cart : carts) {
            CartResp.Inner item = new CartResp.Inner();
            ProductModel product = productDao.getProductById(cart.getProductid());
            item.setId(product.getProductid());
            item.setCartid(cart.getCartid());
            item.setImage(product.getMainpic());
            item.setMode(Integer.valueOf(cart.getBuyrent()));
            item.setNum(cart.getProductcount());
            item.setTitle(product.getDetailname());
            if (Constant.BUY.equals(cart.getBuyrent())) {
                double price = Integer.valueOf(cart.getProductcount()) * Double.valueOf(product.getBuyprice());
                item.setPrice(price + "");
            } else if (Constant.RENT.equals(cart.getBuyrent())) {
                double price = Integer.valueOf(cart.getProductcount()) * Double.valueOf(product.getRentprice());
                item.setPrice(price + "");
            }
            cp.add(item);
        }
        return cp;
    }

    @Override
    public boolean putInCart(CartModel cartModel) {
        try {
            cartDao.save(cartModel);
            logger.info("putin cart success");
            return true;
        } catch (Exception e) {
            logger.error("putin cart failed");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeCart(ReqRemoveCart removeCart) {
        CartModel cart = cartDao.getCartById(removeCart.getCartid());
        if(cart==null)return false;
        if (cart.getUserid().equals(removeCart.getUserid())) {
            cartDao.deleteById(removeCart.getCartid());
            return true;
        }
        return false;
    }
}
