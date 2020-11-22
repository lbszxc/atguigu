package com.lbs.spring.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Administrator
 * @date 2020/8/1 19:37
 * @description
 **/
@Configuration
public class LbsSpringCloudConfig {

   // 该注解表示让RestTemplate有负载均衡的功能，通过调用Ribbon访问Provide集群
   @LoadBalanced
   @Bean
   public RestTemplate getRestTemplate(){

       return new RestTemplate();

   }

}
