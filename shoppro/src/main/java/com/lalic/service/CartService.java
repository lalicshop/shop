package com.lalic.service;

import com.lalic.model.body.CartResp;


public interface CartService {

    CartResp getCartByUserId(String userId);

}
