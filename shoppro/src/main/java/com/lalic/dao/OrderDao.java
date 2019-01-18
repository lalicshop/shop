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

    @Query(value = "UPDATE shop_order SET `status`=4, reachdate=:reachdate WHERE orderid=:orderid", nativeQuery = true)
    @Modifying
    void confirmOrder(@Param(value = "orderid") String orderid, @Param(value = "reachdate") String reachdate);

    @Query(value = "UPDATE shop_order SET `status`=3 ,deliverid=:deliverid,deliverdate=:deliverdate WHERE orderid=:orderid", nativeQuery = true)
    @Modifying
    void deliverOrder(@Param(value = "orderid") String orderid, @Param(value = "deliverid") String deliverid, @Param(value = "deliverdate") String deliverdate);

    @Query(value = "UPDATE shop_order SET retquantity=:retquantity WHERE orderid=:orderid", nativeQuery = true)
    @Modifying
    void updateRetquantity(@Param(value = "orderid") String orderid, @Param(value = "retquantity") String retquantity);

    @Query(value = "UPDATE shop_order SET `status`=2,paydate=:paydate WHERE orderid=:orderid", nativeQuery = true)
    @Modifying
    void confirmPay(@Param(value = "orderid") String orderid,@Param(value = "paydate") String paydate);

    @Query(value = "UPDATE shop_order SET payorderid=:payorderid WHERE orderid=:orderid", nativeQuery = true)
    @Modifying
    void updatePrePayid(@Param(value = "orderid") String orderid, @Param(value = "payorderid") String payorderid);

    @Query(value = "SELECT * FROM shop_order WHERE  `status`=2", nativeQuery = true)
    List<OrderModel> notDeliver();

    @Query(value = "SELECT * FROM shop_order WHERE  deliverid=:deliverid", nativeQuery = true)
    OrderModel orderByDeliverId(@Param(value = "deliverid") String deliverid);

    @Query(value = "SELECT * FROM shop_order WHERE `status`=:statusid", nativeQuery = true)
    List<OrderModel> getOrderByStatus(@Param(value = "statusid") String statusid);

    @Query(value = "UPDATE shop_order SET waitpayid=:newwaitpayid WHERE orderid=:orderid", nativeQuery = true)
    @Modifying
    void updateWaitPayId(@Param(value = "orderid") String orderid, @Param(value = "newwaitpayid") String newwaitpayid);
}
