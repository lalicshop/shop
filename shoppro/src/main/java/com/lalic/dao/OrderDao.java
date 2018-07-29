package com.lalic.dao;

import com.lalic.entity.OrderModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDao
        extends JpaRepository<OrderModel, String>, JpaSpecificationExecutor<OrderModel> {

    @Query(value = "SELECT * FROM shop_order WHERE orderid=:id", nativeQuery = true)
    OrderModel getOrderById(@Param(value = "id") String id);

    @Query(value = "SELECT * FROM shop_order WHERE userid=:userid", nativeQuery = true)
    List<OrderModel> getOrderByUserid(@Param(value = "userid") String userid);

    @Query(value = "SELECT * FROM shop_order WHERE userid=:userid AND `status`=:statusid", nativeQuery = true)
    List<OrderModel> getOrderByStatus(@Param(value = "userid") String userid, @Param(value = "statusid") String statusid);

    @Query(value = "UPDATE shop_order SET `status`=4 WHERE orderid=:orderid", nativeQuery = true)
    @Modifying
    void confirmOrder(@Param(value = "orderid") String orderid);

    @Query(value = "UPDATE shop_order SET `status`=3 ,deliverid=:deliverid WHERE orderid=:orderid", nativeQuery = true)
    @Modifying
    void deliverOrder(@Param(value = "orderid") String orderid, @Param(value = "deliverid") String deliverid);

    @Query(value = "UPDATE shop_order SET retquantity=:retquantity WHERE orderid=:orderid", nativeQuery = true)
    @Modifying
    void updateRetquantity(@Param(value = "orderid") String orderid, @Param(value = "retquantity") String retquantity);

}
