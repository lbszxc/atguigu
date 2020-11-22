package com.lbs.spring.boot.test;

import com.lbs.spring.boot.Emp;
import com.lbs.spring.boot.mapper.EmpMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/7/31 12:32
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Autowired
    private EmpMapper empMapper;

    private Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Test
    public void testMapper() {
        List<Emp> list = empMapper.selectAll();
        for (Emp emp : list) {
            logger.debug(emp.toString());
        }
    }

}
