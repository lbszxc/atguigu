package com.lbs.crowd.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @date 2020/8/6 21:32
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void testSet(){

        ValueOperations operations = stringRedisTemplate.opsForValue();

        operations.set("stuId","10");

    }

    @Test
    public void testExSet(){

        ValueOperations operations = stringRedisTemplate.opsForValue();

        operations.set("name","jack",2000, TimeUnit.SECONDS);


    }

    public void test(){




    }

}
