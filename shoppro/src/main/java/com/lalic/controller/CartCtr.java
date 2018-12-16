package com.lalic.controller;

import com.lalic.entity.CartModel;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.CartResp;
import com.lalic.model.body.ReqOrder;
import com.lalic.model.body.ReqRemoveCart;
import com.lalic.service.CartService;
import com.lalic.util.UserChecker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cart")
public class CartCtr {

    private static final Logger logger = LoggerFactory.getLogger(AddressCtr.class);

    @Autowired
    UserChecker userChecker;

    @Autowired
    CartService cartService;

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public BaseResponse getOrderList(@RequestBody ReqOrder reqOrder) {
        logger.info("cart/all : " + reqOrder.getUserid());
        BaseResponse response = new BaseResponse();
        CartResp cart = cartService.getCartByUserId(reqOrder.getUserid());
        return response.setData(cart);
    }

    @RequestMapping(value = "/putin", method = RequestMethod.POST)
    public BaseResponse putInCart(@RequestBody CartModel reqCart) {
        logger.info("cart/putin : " + reqCart.getUserid());
        BaseResponse response = new BaseResponse();
        if (cartService.putInCart(reqCart))
            return response;
        return response.setData("操作失败");
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public BaseResponse removeCart(@RequestBody ReqRemoveCart removeCart) {
        logger.info("cart/remove : " + removeCart.getUserid());
        BaseResponse response = new BaseResponse();
        if (cartService.removeCart(removeCart))
            return response;
        return response.setData("操作无效").setCode(403);
    }

}
