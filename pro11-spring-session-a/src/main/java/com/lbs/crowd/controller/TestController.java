package com.lbs.crowd.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 * @date 2020/8/13 17:21
 * @description
 **/
@RestController
public class TestController {

    @RequestMapping(value = "/test/spring/session/save")
    public String testSession(HttpSession session){

        session.setAttribute("king","Hello-King");

        return "存入数据";

    }


}
