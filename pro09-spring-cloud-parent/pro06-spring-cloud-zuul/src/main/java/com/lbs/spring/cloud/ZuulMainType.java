package com.lbs.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * @author Administrator
 * @date 2020/8/5 8:50
 * @description
 **/
// 启用Zuul网关代理的功能
@EnableZuulProxy
@SpringBootApplication
public class ZuulMainType {

    public static void main(String[] args) {
        SpringApplication.run(ZuulMainType.class,args);
    }

}
