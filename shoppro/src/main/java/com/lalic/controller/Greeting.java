package com.lalic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/liu")
public class Greeting {

    @RequestMapping(value = "/name")
    public String greeting(String name) {
        return "hahxxxxxxxxxxxxxa";
    }

}
