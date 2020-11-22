package com.stillcrowdfunding.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.stillcrowdfunding.entity.Role;
import com.stillcrowdfunding.service.api.RoleService;
import com.stillcrowdfunding.until.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 * @date 2020/6/23 21:35
 * @description
 **/
//@Controller
//@ResponseBody
@RestController
public class RoleController{

    @Autowired
    private RoleService roleService;

    //@ResponseBody
    @RequestMapping(value = "/role/remove/by/role/id/array.json")
    public ResultEntity<String> removeByRoleIdArray(@RequestBody List<Integer> roleIdList){

        roleService.removeRole(roleIdList);

        return ResultEntity.successWithoutData();
    }

    //@ResponseBody
    @RequestMapping(value = "/role/update.json")
    public ResultEntity<String> updateRole(Role role){

        roleService.updateRole(role);

        return ResultEntity.successWithoutData();
    }

    //@ResponseBody
    @RequestMapping(value = "/role/save.json")
    public ResultEntity<String> saveRole(Role role){

        roleService.saveRole(role);

        return ResultEntity.successWithoutData();

    }

    //@ResponseBody
    @PreAuthorize("hasRole('部长')")
    @RequestMapping(value = "/role/get/page/info.json")
   public ResultEntity<PageInfo<Role>> getPageInfo(
           @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
           @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
           @RequestParam(value = "keyword",defaultValue = "") String keyword){
        PageInfo<Role> pageInfo = null;


        // 调用Service方法获取分页数据
        pageInfo = roleService.getPageInfo(pageNum,pageSize,keyword);

        // 封装到ResultEntity对象中返回（如果上面操作抛出异常，就交给异常映射机制处理）
        return ResultEntity.successWithoutData(pageInfo);

    }

}
