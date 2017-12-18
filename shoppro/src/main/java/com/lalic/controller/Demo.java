package com.lalic.controller;

import com.lalic.dao.StuLikeDao;
import com.lalic.iml.StuIml;
import com.lalic.model.Stu;
import com.lalic.model.StuLike;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/base")
public class Demo {
    private final static Logger logger = LoggerFactory.getLogger(Demo.class);

    @Autowired
    StuIml service;

    @Autowired
    StuLikeDao dao;

    @RequestMapping(value = "/search")
    public List<Stu> greeting(String name) {
        logger.info("greeting");
        return service.getStu();
    }

    @RequestMapping(value = "/like")
    public List<StuLike> getlike(String name) {
        logger.info("getlike");
        return dao.getStuLike();
    }

}
