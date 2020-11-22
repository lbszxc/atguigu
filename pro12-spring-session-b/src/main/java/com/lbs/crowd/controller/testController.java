package com.lbs.crowd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 * @date 2020/8/13 17:25
 * @description
 **/
@RestController
public class testController {

    @RequestMapping(value = "/test/spring/session/retrieve")
    public String testSession(HttpSession session){

        String value = (String) session.getAttribute("king");

        return value;


    }



}
