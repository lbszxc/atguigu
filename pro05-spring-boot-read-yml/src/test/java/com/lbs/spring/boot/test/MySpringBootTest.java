package com.lbs.spring.boot.test;

import com.lbs.spring.boot.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Administrator
 * @date 2020/7/30 11:41
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringBootTest {

    Logger logger = LoggerFactory.getLogger(MySpringBootTest.class);

    @Autowired
    private Student student;

    @Value("${lbs.best.wishes}")
    private String wishes;

    @Test
    public void testReadYml(){

        logger.info(student.toString());

        logger.debug("这是一个错误信息！！！");

    }

    @Test
    public void testReadSimpleValue(){
        logger.info(wishes);
    }

}
