package com.lalic.dao;

import com.lalic.entity.LunBoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LunBoDao
        extends JpaRepository<LunBoModel, String>, JpaSpecificationExecutor<LunBoModel> {

    @Query(value = "SELECT * FROM shop_lunbo", nativeQuery = true)
    List<LunBoModel> getLunBo();

}