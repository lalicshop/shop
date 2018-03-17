package com.lalic.dao;

import com.lalic.entity.OrderDetailModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailDao
        extends JpaRepository<OrderDetailModel, String>, JpaSpecificationExecutor<OrderDetailModel> {

    @Query(value = "SELECT * FROM shop_orderdetail WHERE orderid=:orderid", nativeQuery = true)
    List<OrderDetailModel> getOrderDetailByOrderid(@Param(value = "orderid") String orderid);

}
