package com.stillcrowdfunding.mvc.controller;

import com.stillcrowdfunding.entity.Admin;
import com.stillcrowdfunding.entity.ParamData;
import com.stillcrowdfunding.entity.Student;
import com.stillcrowdfunding.service.api.AdminService;
import com.stillcrowdfunding.until.CrowdUtil;
import com.stillcrowdfunding.until.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Administrator
 * @date 2020/6/14 22:00
 * @description
 **/
@Controller
public class TestController {
    @Autowired
    private AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @ResponseBody
    @RequestMapping(value = "/test/ajax/async.html")
    public String testAsyncAjax() throws InterruptedException {
        Thread.sleep(2000);
        return "success";
    }


    @RequestMapping(value = "/test/ssm.html")
    public String testSsm(ModelMap modelMap, HttpServletRequest request){

        boolean judgeRequest =  CrowdUtil.judgeRequestType(request);

        logger.info("judgeRequest："+judgeRequest);

        List<Admin> adminList = adminService.getAll();

        modelMap.addAttribute("adminList",adminList);

        //创建一个空指针异常
//        String a = null;

//        System.out.println(a.length());

        System.out.println(10/0);
        return "target";
    }
    @ResponseBody
    @RequestMapping(value = "/send/array/one.html")
    public String testReceiveArrayOne(@RequestParam("array[]") List<Integer> array){

        for (Integer number:array){

            System.out.println("number:"+number);

        }

        return "success";
    }
    @ResponseBody
    @RequestMapping(value = "/send/array/two.html")
    public String testReceiveArrayTwo(ParamData paramData){

        List<Integer> array =  paramData.getArray();

        for (Integer number:array){

            logger.info("number:"+number);

        }

        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/send/array/three.html")
    public String testReceiveArrayThree(@RequestBody List<Integer> array){

        for (Integer number:array){

            logger.info("number:"+number);

        }

        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/send/compose/Object.json")
    public ResultEntity<Student> testReceiveComposeObject(@RequestBody Student student,HttpServletRequest request){

        boolean judgeRequest =  CrowdUtil.judgeRequestType(request);

        logger.info("judgeRequest："+judgeRequest);

        logger.info(student.toString());

        //将“查询”的Student对象封装到ResultEntity中返回
        ResultEntity<Student> resultEntity = ResultEntity.successWithoutData(student);

        return resultEntity;
    }
}
