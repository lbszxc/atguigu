package com.lbs.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.lbs.spring.boot02.controller")
@SpringBootApplication
public class TestSpringBoot {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringBoot.class, args);
    }

}
