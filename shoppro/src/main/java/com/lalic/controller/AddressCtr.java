package com.lalic.controller;

import com.lalic.entity.AddressModel;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.ReqOrder;
import com.lalic.service.AddressService;
import com.lalic.util.UserChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressCtr {

    @Autowired
    UserChecker userChecker;

    @Autowired
    AddressService addressService;


    @RequestMapping(value = "/fetch", method = RequestMethod.POST)
    public BaseResponse getAddressList(@RequestBody ReqOrder reqOrder) {
        BaseResponse response = new BaseResponse();
        List<AddressModel> address = addressService.getAddressByUserid(reqOrder.getUserid());
        return response.setData(address);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse addAddress(@RequestBody AddressModel address) {
        BaseResponse response = new BaseResponse();
        addressService.addAddress(address);
        return response;
    }

    @RequestMapping(value = "/delete/{addressid}", method = RequestMethod.POST)
    public BaseResponse deleteAddress(@RequestBody ReqOrder reqOrder, @PathVariable String addressid) {
        BaseResponse response = new BaseResponse();
        addressService.deleteAddress(addressid);
        return response;
    }

    @RequestMapping(value = "/setdefault/{addressid}", method = RequestMethod.POST)
    public BaseResponse setDefault(@RequestBody ReqOrder reqOrder, @PathVariable String addressid) {
        return addressService.setDefault(reqOrder.getUserid(), addressid);
    }

}
