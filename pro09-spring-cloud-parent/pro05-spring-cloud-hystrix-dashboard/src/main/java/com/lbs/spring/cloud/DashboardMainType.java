package com.lbs.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author Administrator
 * @date 2020/8/4 21:05
 * @description
 *
 **/
// 启用Hystrix 仪表盘功能
@EnableHystrixDashboard
@SpringBootApplication
public class DashboardMainType {

    public static void main(String[] args) {
        SpringApplication.run(DashboardMainType.class,args);
    }

}
