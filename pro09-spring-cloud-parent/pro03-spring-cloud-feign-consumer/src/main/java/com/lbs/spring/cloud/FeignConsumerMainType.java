package com.lbs.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Administrator
 * @date 2020/8/1 19:32
 * @description
 **/
// 启用Feign 客户端功能
@EnableFeignClients
@SpringBootApplication
public class FeignConsumerMainType {

    public static void main(String[] args) {

        SpringApplication.run(FeignConsumerMainType.class,args);

    }

}
