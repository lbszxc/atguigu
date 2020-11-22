package com.lbs.test;

import com.lbs.entity.Employee;
import com.lbs.spring.MyAnnotationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Administrator
 * @date 2020/7/25 9:56
 * @description
 **/
public class SpringTest {

    public static void main(String[] args){

        //以前是使用 new ClassPathXmlApplicationContext("");方式加载XML配置文件

        // 现在基于注解配置类创建IOC容器对象
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MyAnnotationConfiguration.class);

        // 从IOC容器中获取bean

        Employee employee = applicationContext.getBean(Employee.class);
        System.out.println(employee);

        applicationContext.close();

    }

}
