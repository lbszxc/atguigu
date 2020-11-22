package com.lbs.spring.boot02.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2020/7/30 9:16
 * @description
 **/
@RestController
public class HelloController {

    @RequestMapping(value = "/get/spring/boot/hello/message")
    public String getMessage(){

        return "这是我创建的SpringBoot程序！";

    }

}
