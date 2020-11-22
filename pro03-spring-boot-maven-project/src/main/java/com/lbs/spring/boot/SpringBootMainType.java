package com.lbs.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 * @date 2020/7/29 21:34
 * @description
 **/
// 将当前类标记为一个SpringBoot应用
@SpringBootApplication
public class SpringBootMainType {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootMainType.class,args);

    }

}
