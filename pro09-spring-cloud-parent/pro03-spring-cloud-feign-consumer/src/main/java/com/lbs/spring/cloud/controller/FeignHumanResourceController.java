package com.lbs.spring.cloud.controller;

import com.lbs.spring.cloud.api.EmployeeRemoteService;
import com.lbs.spring.cloud.entity.Employee;
import com.lbs.spring.cloud.until.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/3 17:38
 * @description
 **/
@RestController
public class FeignHumanResourceController {

    // 装配远程调用的服务接口，后面向调用本地方法一样使用
    @Autowired
    private EmployeeRemoteService employeeRemoteService;

    @RequestMapping(value = "/feign/consumer/test/fallback")
    public ResultEntity<Employee> testFallBack(@RequestParam("signal") String signal){

        return employeeRemoteService.getEmployeeWithCircuitBreaker(signal);

    }

    @RequestMapping(value = "/feign/consumer/get/emp")
    public Employee getEmployeeRemote(){

        return employeeRemoteService.getEmployeeRemote();

    }

    @RequestMapping(value = "/feign/consumer/search/emp")
    public List<Employee> getEmployeeListRemote(String keyword){

        return employeeRemoteService.getEmployeeListRemote(keyword);

    }

}
