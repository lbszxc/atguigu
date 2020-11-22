package com.lbs.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 * @date 2020/7/31 12:24
 * @description
 **/
@SpringBootApplication
@MapperScan("com.lbs.spring.boot.mapper")
public class SpringBootMainClass {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootMainClass.class,args);

    }

}
