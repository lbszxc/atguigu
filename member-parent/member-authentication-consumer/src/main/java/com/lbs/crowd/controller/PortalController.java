package com.lbs.crowd.controller;

import com.lbs.crowd.api.MySQLRemoteService;
import com.lbs.crowd.entity.vo.PortalTypeVO;
import com.stillcrowdfunding.constant.CrowdConstant;
import com.stillcrowdfunding.until.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/8/7 10:04
 * @description
 **/
@Controller
public class PortalController {

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @RequestMapping(value = "/")
    public String showPortalPage(Model model){

        // 1.调用mySQLRemoteService提供方法要查询的数据
        ResultEntity<List<PortalTypeVO>> resultEntity = mySQLRemoteService.getPortalTypeProjectDataRemote();

        // 2.检查结果
        String result = resultEntity.getResult();
        if (ResultEntity.SUCCESS.equals(result)){

            // 3.获取查询结果数据
            List<PortalTypeVO> list = resultEntity.getData();

            // 4.存入model模型
            model.addAttribute(CrowdConstant.ATTR_NAME_PROTAL_DATA,list);


        }

        return "portal";
    }


}
