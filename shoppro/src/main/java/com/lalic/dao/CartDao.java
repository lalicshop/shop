package com.lalic.dao;

import com.lalic.entity.CartModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartDao
        extends JpaRepository<CartModel, String>, JpaSpecificationExecutor<CartModel> {


    @Query(value = "SELECT * FROM shop_cart WHERE userid=:userid", nativeQuery = true)
    List<CartModel> getCartByUserId(@Param(value = "userid") String userid);


}
