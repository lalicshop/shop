package com.lalic.dao;

import com.lalic.model.StuLike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StuLikeDao extends JpaRepository<StuLike, String>, JpaSpecificationExecutor<StuLike> {

    @Query(value = "SELECT s.name,s.age,t.like FROM stu s JOIN t_like t on s.age=t.age", nativeQuery = true)
    List<StuLike> getStuLike();

}
