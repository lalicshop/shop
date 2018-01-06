package com.lalic.dao;

import com.lalic.model.JingXuanRespData;
import com.lalic.entity.ProductModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JingXuanDao
        extends JpaRepository<ProductModel, String>, JpaSpecificationExecutor<ProductModel> {

    @Query(value = "SELECT * FROM shop_jingxuan", nativeQuery = true)
    List<JingXuanRespData> getJingXuan();

}