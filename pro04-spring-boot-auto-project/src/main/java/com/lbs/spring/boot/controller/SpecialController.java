package com.lbs.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2020/7/30 10:10
 * @description
 **/
@RestController
public class SpecialController {

    @RequestMapping(value = "/special")
    public String getMessage(){

        return "测试SpringBoot约定规则！";

    }

}
