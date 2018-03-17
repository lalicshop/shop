package com.lalic.dao;

import com.lalic.entity.JingXuan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JingXuanDao
        extends JpaRepository<JingXuan, String>, JpaSpecificationExecutor<JingXuan> {
    @Query(value = "SELECT shop_product.productid,shop_product.shortname,shop_product.buyprice,shop_product.rentprice,shop_product.mainpic FROM shop_product WHERE shop_product.productid IN (SELECT productid FROM shop_jingxuan)", nativeQuery = true)
    List<JingXuan> getJingXuan();

}
