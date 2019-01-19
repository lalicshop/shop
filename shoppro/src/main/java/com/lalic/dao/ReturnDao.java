package com.lalic.dao;

import com.lalic.entity.ReturnModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReturnDao
        extends JpaRepository<ReturnModel, String>, JpaSpecificationExecutor<ReturnModel> {


    @Query(value = "SELECT * FROM shop_return WHERE userid=:userid", nativeQuery = true)
    List<ReturnModel> getByUserid(@Param(value = "userid") String userid);

    @Modifying
    @Query(value = "UPDATE shop_return SET isretmoney=1,money=:money,reachdate=:reachdate,`status`=6 WHERE deliverno=:deliverno", nativeQuery = true)
    int confirmRet(@Param(value = "money") String money,
                                 @Param(value = "deliverno") String deliverno,
                                 @Param(value = "reachdate") String reachdate);

    @Query(value = "SELECT * FROM shop_return WHERE deliverno=:deliverno", nativeQuery = true)
    ReturnModel findByDeliverNo(@Param(value = "deliverno") String deliverno);
}
