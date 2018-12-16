package com.lalic.iml;

import com.lalic.dao.AddressDao;
import com.lalic.entity.AddressModel;
import com.lalic.service.AddressService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddressServiceIml implements AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceIml.class);


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
            logger.info("address save, userid is "+address.getUserid());
            addressDao.save(address);
        } else {
            logger.info("address update, userid is "+address.getUserid());
            addressDao.update(address.getUserid(), address.getProvince(), address.getCity(), address.getDistrict(), address.getDetail(),address.getUsername(),address.getPhone());
        }
    }

    @Override
    @Transactional
    public void deleteAddress(String addressid) {
        addressDao.deleteByAddressid(addressid);
    }

}
