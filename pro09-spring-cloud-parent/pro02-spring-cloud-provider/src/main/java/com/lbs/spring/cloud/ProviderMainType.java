package com.lbs.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Administrator
 * @date 2020/8/1 18:40
 * @description
 **/
// 两个注解功能大致相同
// @EnableDiscoveryClient
// 说明：当SpringCloud版本较低要用到该注解，版本高可以省略，表示启用发现服务的功能，不局限于Eureka注册中心
// @EnableEurekaClient
//说明：当SpringCloud版本较低要用到该注解，版本高可以省略，表示启用Eureka客户端，必须是Eureka注册中心
// 使用注解开启断路器的功能
@EnableCircuitBreaker
@SpringBootApplication
public class ProviderMainType {

    public static void main(String[] args) {

        SpringApplication.run(ProviderMainType.class,args);

    }

}
