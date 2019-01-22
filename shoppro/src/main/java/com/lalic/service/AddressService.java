package com.lalic.service;

import com.lalic.entity.AddressModel;


public interface AddressService {

    AddressModel getAddressByUserid(String userId);

    void upsertAddress(AddressModel address);

    void deleteAddress(String addressid);


    String getAddressByPhone(String phone);
}
