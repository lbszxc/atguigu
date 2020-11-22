package com.stillcrowdfunding.mvc.controller;

import com.stillcrowdfunding.entity.Auth;
import com.stillcrowdfunding.entity.Role;
import com.stillcrowdfunding.service.api.AdminService;
import com.stillcrowdfunding.service.api.AuthService;
import com.stillcrowdfunding.service.api.RoleService;
import com.stillcrowdfunding.until.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020/7/20 22:25
 * @description
 **/
@Controller
public class AssignController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @ResponseBody
    @RequestMapping(value = "/assign/do/role/assign/auth.json")
    public ResultEntity<String> saveRoleAuthRelationship(
            @RequestBody Map<String,List<Integer>> map){

        authService.saveRoleAuthRelationship(map);

        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping(value = "/assign/get/assigned/auth/id/by/role/id.json")
    public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(
            @RequestParam("roleId") Integer roleId){

        List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);

        return ResultEntity.successWithoutData(authIdList);
    }

    @ResponseBody
    @RequestMapping(value = "/assign/get/all/auth.json")
    public ResultEntity<List<Auth>> getAllAuth(){

        List<Auth> authList = authService.getAll();

        return ResultEntity.successWithoutData(authList);
    }

    @RequestMapping(value = "/assign/do/role/assign.html")
    public String saveAdminRoleRelationship(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword,

            // 我们允许用户在页面上取消所有已分配角色再提交表单，可以不提供roleIdList请求参数
            // 设置了required = false表示这个请求不是必须的
            @RequestParam(value = "roleIdList",required = false) List<Integer> roleIdList){

        adminService.saveAdminRoleRelationship(adminId,roleIdList);

        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;

    }

    @RequestMapping(value = "/assign/to/assign/role/page.html")
    public String toAssignRolePage(
            @RequestParam("adminId") Integer adminId,
            ModelMap modelMap){

        // 1.查询已经分配的角色
        List<Role> assignRoleList = roleService.getAssignedRole(adminId);

        // 2.查询未分配的角色
        List<Role> unAssignRoleList = roleService.getUnAssignedRole(adminId);

        // 3.存入模型（本质上其实是：request.setAttribute("attrName","attrValue")）
        modelMap.addAttribute("assignRoleList",assignRoleList);
        modelMap.addAttribute("unAssignRoleList",unAssignRoleList);

        return "assign_role";
    }
}
