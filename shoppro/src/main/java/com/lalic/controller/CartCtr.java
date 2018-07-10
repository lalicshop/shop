package com.lalic.controller;

import com.lalic.model.BaseResponse;
import com.lalic.model.body.CartResp;
import com.lalic.model.body.ReqOrder;
import com.lalic.service.CartService;
import com.lalic.util.UserChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cart")
public class CartCtr {

    @Autowired
    UserChecker userChecker;

    @Autowired
    CartService cartService;

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public BaseResponse getOrderList(@RequestBody ReqOrder reqOrder) {
        BaseResponse response = new BaseResponse();
        CartResp cart = cartService.getCartByUserId(reqOrder.getUserid());
        return response.setData(cart);
    }

}
