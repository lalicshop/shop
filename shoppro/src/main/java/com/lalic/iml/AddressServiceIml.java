package com.lalic.iml;

import com.lalic.dao.AddressDao;
import com.lalic.entity.AddressModel;
import com.lalic.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddressServiceIml implements AddressService {

    @Autowired
    AddressDao addressDao;

    @Override
    public AddressModel getAddressByUserid(String userId) {
        return addressDao.getAddressByUserId(userId);
    }

    @Override
    @Transactional
    public void upsertAddress(AddressModel address) {
        AddressModel addressRes = addressDao.getAddressByUserId(address.getUserid());
        if (addressRes == null) {
            addressDao.save(address);
        } else {
            addressDao.update(address.getUserid(), address.getProvince(), address.getCity(), address.getDistrict(), address.getDetail());
        }

    }

    @Override
    @Transactional
    public void deleteAddress(String addressid) {
        addressDao.deleteByAddressid(addressid);
    }

}
