package com.lalic.dao;

import com.lalic.entity.AddressModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressDao
        extends JpaRepository<AddressModel, String>, JpaSpecificationExecutor<AddressModel> {

    @Query(value = "SELECT * FROM shop_address WHERE userid=:userid", nativeQuery = true)
    AddressModel getAddressByUserId(@Param(value = "userid") String userid);

    @Query(value = "SELECT phone FROM shop_address WHERE userid=:userid", nativeQuery = true)
    String getPhoneByUserId(@Param(value = "userid") String userid);

    @Query(value = "SELECT * FROM shop_address WHERE addressid=:addressid", nativeQuery = true)
    AddressModel getAddressId(@Param(value = "addressid") String addressid);

    @Query(value = "DELETE FROM shop_address WHERE addressid=:addressid", nativeQuery = true)
    @Modifying
    void deleteByAddressid(@Param(value = "addressid") String addressid);

    @Query(value = "UPDATE shop_address SET province=:province,city=:city,district=:district,detail=:detail,username=:username,phone=:phone WHERE userid=:userid", nativeQuery = true)
    @Modifying
    void update(@Param(value = "userid") String userid,
                @Param(value = "province") String province,
                @Param(value = "city") String city,
                @Param(value = "district") String district,
                @Param(value = "detail") String detail,
                @Param(value = "username") String username,
                @Param(value = "phone") String phone);

}
