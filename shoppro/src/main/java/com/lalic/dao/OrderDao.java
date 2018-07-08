package com.lalic.dao;

import com.lalic.entity.OrderModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDao
        extends JpaRepository<OrderModel, String>, JpaSpecificationExecutor<OrderModel> {


    @Query(value = "SELECT * FROM shop_order WHERE orderid=:id", nativeQuery = true)
    OrderModel getOrderById(@Param(value = "id") String id);

    @Query(value = "SELECT * FROM shop_order WHERE userid=:userid", nativeQuery = true)
    List<OrderModel> getOrderByUserid(@Param(value = "userid") String userid);

    @Query(value = "SELECT 'status' FROM shop_order WHERE userid=:userid", nativeQuery = true)
    String getStatusByOrderID(@Param(value = "userid") String userid);

}
