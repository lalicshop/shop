package com.lalic.service;

import com.lalic.entity.AddressModel;
import com.lalic.model.BaseResponse;

import java.util.List;


public interface AddressService {

    List<AddressModel> getAddressByUserid(String userId);

    void addAddress(AddressModel address);

    void deleteAddress(String addressid);

    BaseResponse setDefault(String userid, String addressid);

}
