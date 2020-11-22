package com.lbs.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Administrator
 * @date 2020/8/1 8:52
 * @description
 **/
@Controller
public class TemplateController {

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = "/test/thymeleaf")
    public String testThymeleaf(ModelMap modelMap, HttpSession session){

        // 1.将测试数据存入请求域
        modelMap.addAttribute("attrNameRequestScope","<p style='color:blue;font-size:24px;'>attrNameRequestScope</p>");

        // 2.将测试数据存入会话域
        session.setAttribute("attrNameSessionScope","attrNameSessionScope");

        // 3.将测试数据存入应用域
        servletContext.setAttribute("attrNameAppScope","attrNameAppScope");

        // 4.为了测试集合遍历，把集合存入请求域
        modelMap.addAttribute("list", Arrays.asList("jack","tom","mark"));

        return "hello";

    }


}
