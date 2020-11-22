package com.lbs.spring.cloud.controller;

import com.lbs.spring.cloud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Administrator
 * @date 2020/8/1 19:43
 * @description
 **/
@RestController
public class HumanResourceController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer/ribbon/get/employee/remote")
    public Employee getEmployeeRemote(){

        // 1.声明远程微服务的主机地址和端口号
        //String host = "http://localhost:1000";

        // 将远程微服务调用的地址从“IP地址+端口号”改成“微服务的名称”
        String host = "http://lbs-provider";

        // 2.声明具体要调用功能的URL地址
        String url = "/provider/get/employee/remote";

        // 3.通过RestTemplate对象调用微服务
        return restTemplate.getForObject(host + url,Employee.class);
    }

}
