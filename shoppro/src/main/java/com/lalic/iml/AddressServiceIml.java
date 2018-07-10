package com.lalic.iml;

import com.lalic.dao.AddressDao;
import com.lalic.entity.AddressModel;
import com.lalic.model.BaseResponse;
import com.lalic.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.transaction.Transactional;

@Component
public class AddressServiceIml implements AddressService {

    @Autowired
    AddressDao addressDao;

    @Override
    public List<AddressModel> getAddressByUserid(String userId) {
        return addressDao.getAddressByUserId(userId);
    }

    @Override
    public void addAddress(AddressModel address) {
        addressDao.save(address);
    }

    @Override
    @Transactional
    public void deleteAddress(String addressid) {
        addressDao.deleteByAddressid(addressid);
    }

    @Override
    @Transactional
    public BaseResponse setDefault(String userid, String addressid) {
        BaseResponse response=new BaseResponse();
        AddressModel address = addressDao.getAddressId(addressid);
        if (!userid.equals(address.getUserid())) {
            response.setCode(403);
            response.setMess("非法操作");
            return response;
        }
        addressDao.setAllNotDefault(userid);
        addressDao.setDefault(addressid);
        return response;
    }
}
