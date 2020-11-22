package com.lbs.spring.cloud.factory;

import com.lbs.spring.cloud.api.EmployeeRemoteService;
import com.lbs.spring.cloud.entity.Employee;
import com.lbs.spring.cloud.until.ResultEntity;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 1.实现Consumer服务降级功能
 * 2.实现FallBackFactory接口时要传入@FeignClient注解标记的接口类型
 * 3.实现create()方法中返回@FeignClient注解标记的接口类型对象，当Provider调用失败后，会执行这个对象的对应方法
 * 4.这个类必须使用@Component注解将当前这个类的对象加入到IOC容器中，当然这个类必须能够被IOC容器扫描到
 * @author Administrator
 * @date 2020/8/4 17:37
 * @description
 **/
@Component
public class MyFallBackFactory implements FallbackFactory<EmployeeRemoteService> {

    public EmployeeRemoteService create(final Throwable throwable) {
        return new EmployeeRemoteService() {

            public Employee getEmployeeRemote() {
                return null;
            }

            public List<Employee> getEmployeeListRemote(String keyword) {
                return null;
            }

            public ResultEntity<Employee> getEmployeeWithCircuitBreaker(String signal) {

                return ResultEntity.failed("降级机制生效："+throwable.getMessage());

            }
        };
    }
}

