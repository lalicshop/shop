package com.lalic.dao;

import com.lalic.entity.AddressModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressDao
        extends JpaRepository<AddressModel, String>, JpaSpecificationExecutor<AddressModel> {

    @Query(value = "SELECT * FROM shop_address WHERE userid=:userid", nativeQuery = true)
    List<AddressModel> getAddressByUserId(@Param(value = "userid") String userid);

    @Query(value = "SELECT * FROM shop_address WHERE addressid=:addressid", nativeQuery = true)
    AddressModel getAddressId(@Param(value = "addressid") String addressid);

    @Query(value = "DELETE FROM shop_address WHERE addressid=:addressid", nativeQuery = true)
    @Modifying
    void deleteByAddressid(@Param(value = "addressid") String addressid);

    @Query(value = "UPDATE shop_address SET isdefault='1' WHERE userid=:userid", nativeQuery = true)
    @Modifying
    void setAllNotDefault(@Param(value = "userid") String userid);

    @Query(value = "UPDATE shop_address SET isdefault='0' WHERE addressid=:addressid", nativeQuery = true)
    @Modifying
    void setDefault(@Param(value = "addressid")String addressid);
}
