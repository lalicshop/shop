package com.lalic.dao;

import com.lalic.model.Stu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StuDao extends JpaRepository<Stu,String>,JpaSpecificationExecutor<Stu> {

    @Query(value = "SELECT * FROM stu",nativeQuery = true)
    List<Stu> getStu();

}
