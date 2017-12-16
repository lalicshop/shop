package com.lalic.controller;

import com.lalic.iml.StuIml;
import com.lalic.model.Stu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/base")
public class Demo {

    @Autowired
    StuIml service;

    @RequestMapping(value = "/search")
    public List<Stu> greeting(String name) {
        //this is test
        return service.getStu();
    }

}
