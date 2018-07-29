package com.lalic.controller;

import com.lalic.entity.AddressModel;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.ReqOrder;
import com.lalic.service.AddressService;
import com.lalic.util.UserChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/address")
public class AddressCtr {

    @Autowired
    UserChecker userChecker;

    @Autowired
    AddressService addressService;


    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public BaseResponse getAddressList(@RequestBody ReqOrder reqOrder) {
        BaseResponse response = new BaseResponse();
        AddressModel address = addressService.getAddressByUserid(reqOrder.getUserid());
        return response.setData(address);
    }

    @RequestMapping(value = "/upsert", method = RequestMethod.POST)
    public BaseResponse addAddress(@RequestBody AddressModel address) {
        BaseResponse response = new BaseResponse();
        addressService.upsertAddress(address);
        return response;
    }

}
