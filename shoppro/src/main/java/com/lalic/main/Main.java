package com.lalic.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(value = {"com.lalic"})
@EntityScan("com.lalic.model")
@EnableJpaRepositories("com.lalic.dao")
@ServletComponentScan(basePackages = "com.lalic")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}