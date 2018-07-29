package com.lalic.service;

import com.lalic.entity.CartModel;
import com.lalic.model.body.CartResp;
import com.lalic.model.body.ReqRemoveCart;


public interface CartService {

    CartResp getCartByUserId(String userId);

    boolean putInCart(CartModel cartModel);

    boolean removeCart(ReqRemoveCart removeCart);

}
