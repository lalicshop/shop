package com.lalic.dao;

import com.lalic.model.LunBoRespData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LunBoDao
        extends JpaRepository<LunBoRespData, String>, JpaSpecificationExecutor<LunBoRespData> {

    @Query(value = "SELECT * FROM shop_lunbo", nativeQuery = true)
    List<LunBoRespData> getLunBo();

}