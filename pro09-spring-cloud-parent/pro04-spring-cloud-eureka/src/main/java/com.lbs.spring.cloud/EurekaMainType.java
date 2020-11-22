package com.lbs.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Administrator
 * @date 2020/8/2 9:28
 * @description
 **/
// 区域Eureka服务器功能
@EnableEurekaServer
@SpringBootApplication
public class EurekaMainType {

    public static void main(String[] args) {
        SpringApplication.run(EurekaMainType.class,args);
    }

}
