package com.lalic.controller;

import com.alibaba.fastjson.JSON;
import com.lalic.entity.AddressModel;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.ReqOrder;
import com.lalic.service.AddressService;
import com.lalic.util.UserChecker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/address")
public class AddressCtr {

    private static final Logger logger = LoggerFactory.getLogger(AddressCtr.class);

    @Autowired
    UserChecker userChecker;

    @Autowired
    AddressService addressService;


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public BaseResponse getAddressList(@RequestBody ReqOrder reqOrder) {
        logger.info("address/search : " + reqOrder.getUserid());
        BaseResponse response = new BaseResponse();
        AddressModel address = addressService.getAddressByUserid(reqOrder.getUserid());
        logger.info("address is : " + JSON.toJSONString(address));
        return response.setData(address);
    }

    @RequestMapping(value = "/upsert", method = RequestMethod.POST)
    public BaseResponse addAddress(@RequestBody AddressModel address) {
        logger.info("address/upsert : " + address.getUserid());
        BaseResponse response = new BaseResponse();
        addressService.upsertAddress(address);
        return response;
    }

}
