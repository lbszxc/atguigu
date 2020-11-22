package com.lbs.spring.cloud.api;

import com.lbs.spring.cloud.entity.Employee;
import com.lbs.spring.cloud.factory.MyFallBackFactory;
import com.lbs.spring.cloud.until.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/3 17:06
 * @description
 **/

//@FeignClient注解表示当前接口和一个Provider对应
//          注解中value属性指定要调用Provider的微服务的名称
//          注解中fallbackFactory属性指定Provider不可用时提供备用方案的工厂类型
@FeignClient(value = "lbs-provider",fallbackFactory = MyFallBackFactory.class)
public interface EmployeeRemoteService {

    //远程调用的方法
    //要求@RequestMapping注解映射的地址要一致
    //要求方法的声明要一致
    //用来获取请求的参数的注解@RequestMapping、@RequestBody、PathVariable不能省略，两边要一致
    @RequestMapping(value = "/provider/select/employee/remote")
    public Employee getEmployeeRemote();

    @RequestMapping(value = "/provider/seach/employee/list/remote")
    List<Employee> getEmployeeListRemote(@RequestParam("keyword") String keyword);

    @RequestMapping(value = "/provider/get/employee/with/circuit/breaker")
    public ResultEntity<Employee> getEmployeeWithCircuitBreaker(@RequestParam("signal") String signal);
}
