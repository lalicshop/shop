package com.lalic.dao;

import com.lalic.entity.ProductModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao
        extends JpaRepository<ProductModel, String>, JpaSpecificationExecutor<ProductModel> {
    @Query(value = "SELECT * FROM shop_product", nativeQuery = true)
    List<ProductModel> getAllProduct();

    @Query(value = "SELECT * FROM shop_product WHERE productid IN (SELECT productid FROM shop_jingxuan)", nativeQuery = true)
    List<ProductModel> getJingXuan();
}
