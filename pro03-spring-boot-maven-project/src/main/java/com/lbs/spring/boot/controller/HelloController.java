package com.lbs.spring.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2020/7/29 21:41
 * @description
 **/
@RestController
public class HelloController {

    @RequestMapping(value = "/get/spring/boot/hello/message")
    public String getMassage(){

        return "This is my first procedure!";

    }

}
