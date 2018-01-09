package com.lalic.iml;

import com.lalic.dao.StuDao;
import com.lalic.entity.Stu;
import com.lalic.service.StuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StuIml implements StuService {

    @Autowired
    StuDao dao;

    @Override
    public List<Stu> getStu() {
        return dao.getStu();
    }
}
