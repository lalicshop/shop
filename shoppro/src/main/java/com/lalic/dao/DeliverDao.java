package com.lalic.dao;

import com.lalic.entity.DeliverModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeliverDao
        extends JpaRepository<DeliverModel, String>, JpaSpecificationExecutor<DeliverModel> {

    @Query(value = "SELECT * FROM shop_deliver WHERE orderid=:orderid", nativeQuery = true)
    DeliverModel getDeliverByOrderId(@Param(value = "orderid") String orderid);

}
