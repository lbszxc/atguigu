package com.lbs.spring.boot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Administrator
 * @date 2020/7/31 21:31
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private RedisTemplate<String,String> myStringRedisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void testRedisTemplate(){

        // 1.用来操作String类型数据的ValueOperations对象
        ValueOperations<Object,Object> valueOperations = redisTemplate.opsForValue();

        // 2.借助ValueOperations对象存入数据
        Object key = "My";
        Object value = "friend";
        valueOperations.set(key,value);

        // 3.读取刚才设置的数据
        Object readValue = valueOperations.get(key);
        System.out.println(readValue);

    }

    @Test
    public void testMyStringRedisTemplate(){

        // 1.创建ValueOperations<String,String>类型的对象
        ValueOperations<String,String> operations = myStringRedisTemplate.opsForValue();

        // 2.借助ValueOperations对象存入数据
        String key = "Hello!";
        String value = "Hi!";
        operations.set(key,value);

        String readValue = operations.get(key);

        System.out.println(readValue);

    }

    @Test
    public void testStringRedisTemplate(){

        // 1.获取ValueOperations对象
        ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();

        // 2.存入数据
        String key = "KEY";
        String value = "VALUE";
        operations.set(key,value);

        // 3.打印value值
        String readValue = operations.get(key);
        System.out.println(value);

    }

    @Test
    public void testListOperations(){

        // 1.创建ValueOperations<String,String>类型的对象
        ListOperations<String,String> operationsList = stringRedisTemplate.opsForList();

        operationsList.leftPush("fruit","apple");
        operationsList.leftPush("fruit","banana");
        operationsList.leftPush("fruit","pear");
        
    }

}
