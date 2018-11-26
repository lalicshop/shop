package com.lalic.dao;

import com.lalic.entity.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao
        extends JpaRepository<UserModel, String>, JpaSpecificationExecutor<UserModel> {


    @Query(value = "SELECT * FROM shop_user WHERE userid=:userid", nativeQuery = true)
    UserModel getUserById(@Param(value = "userid") String userid);


}
