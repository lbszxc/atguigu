package com.lbs.spring;


import com.lbs.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2020/7/25 9:41
 * @description
 **/

// 表示当前这个类是一个配置类，作用大致相当于以前的spring-context.xml
@Configuration
public class MyAnnotationConfiguration {

    // @Bean注解相当于做了下面XML标签的配置，把方法的返回值放到IOC容器中去
    // <bean id = "emp" class = "com.atguigu.entity.Employee">

    @Bean
    public Employee getEmployee(){
        return new Employee();
    }


}
