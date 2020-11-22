package com.lbs.spring.cloud.controller;

import com.lbs.spring.cloud.entity.Employee;
import com.lbs.spring.cloud.until.ResultEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/1 18:43
 * @description
 **/
@RestController
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    // @HystrixCommand注解通过fallbackMethod属性指定断路情况下要调用的备份方法
    @HystrixCommand(fallbackMethod = "getEmployeeWithCircuitBreakerBackup")
    @RequestMapping(value = "/provider/get/employee/with/circuit/breaker")
    public ResultEntity<Employee> getEmployeeWithCircuitBreaker(@RequestParam("signal") String signal) throws InterruptedException {

        if ("quick-bang".equals(signal)){
            throw new RuntimeException();
        }

        if ("slow-bang".equals(signal)){
            Thread.sleep(5000);
        }

        return ResultEntity.successWithData(new Employee(6,"tom666",666.6));

    }

    public ResultEntity<Employee> getEmployeeWithCircuitBreakerBackup(@RequestParam("signal") String signal){

        String message = "方法出现问题，执行熔断！ signal = "+signal;

        return ResultEntity.failed(message);

    }

    @RequestMapping(value = "/provider/select/employee/remote")
    public Employee getEmployeeRemote(){

        return new Employee(05,"tom555 ",555.55);

    }

    @RequestMapping(value = "/provider/seach/employee/list/remote")
    public List<Employee> getEmployeeListRemote(@RequestParam("keyword") String keyword){

        logger.info("keyword = "+keyword);

        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(1,"Employee11",111.11));
        employeeList.add(new Employee(2,"Employee22",222.22));
        employeeList.add(new Employee(3,"Employee33",333.33));

        return employeeList;

    }

    @RequestMapping(value = "/provider/get/employee/remote")
    public Employee getEmployeeRemote(HttpServletRequest request){

        // 1.获取当前服务的端口号
        int serverPort = request.getServerPort();

        return new Employee(05,"tom555 "+serverPort,555.55);

    }


}
