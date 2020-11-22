package com.stillcrowdfunding.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.stillcrowdfunding.constant.CrowdConstant;
import com.stillcrowdfunding.entity.Admin;
import com.stillcrowdfunding.service.api.AdminService;
import com.stillcrowdfunding.until.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Administrator
 * @date 2020/6/17 12:41
 * @description
 **/
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin/update.html")
    public String updateAdmin(
            Admin admin,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("keyword") String keyword){

        adminService.updateAdmin(admin);

        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;

    }

    @RequestMapping(value = "/admin/to/edit/page.html")
    public String toEditPage(
            @RequestParam("adminId") Integer adminId,
            ModelMap modelMap){

            // 1.根据adminId查询Admin对象
            Admin admin = adminService.getAdminById(adminId);

            // 2.把Admin对象存入模型
            modelMap.addAttribute("admin",admin);

            // 3.返回admin-edit视图
            return "admin-edit";

    }

    @PreAuthorize("hasAuthority('user:save')")
    @RequestMapping(value = "/admin/save.html")
    public String saveAdmin(Admin admin){

        adminService.saveAdmin(admin);

        return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;

    }

    @RequestMapping(value = "/admin/remove/{adminId}/{pageNum}/{keyword}.html")
    public String removeAdmin(
            @PathVariable("adminId") Integer adminId,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("keyword") String keyword){

        // 执行删除
        adminService.removeAdmin(adminId);

        // 跳转页面：回到分页页面
        // 尝试方案1：直接跳转到admin-page.jsp页面会无法显示分页数据
        //return "admin-page";

        //尝试方法2：跳转到/admin/get/page.html地址，一旦重新刷新页面就会重复执行删除操作，浪费性能
        //return "forward:/admin/get/page.html";

        // 尝试方法3：重定向到/admin/get/page.html地址
        // 同时为了保持原本所在的页面和查询的关键词在附加pageNum和keyword两个参数
        return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
    }

    @RequestMapping(value = "/admin/get/page.html")
    public String getPageInfo(

            // 使用@RequestParam注解的defaultValue属性，指定默认值，在请求中没有携带对应的参数使用默认值
            // keyword"默认值使用字符串，和SQL语句配合实现两种情况适配
            @RequestParam(value = "keyword",defaultValue = "") String keyword,

            // pageNum默认值使用1
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,

            // pageSize默认值使用5
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,

            ModelMap modelMap){

        // 调用Service方法获取PageInfo对象
        PageInfo<Admin> pageInfo =  adminService.getPageInfo(keyword,pageNum,pageSize);

        // 将PageInfo对象ModelMap模型
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,pageInfo);

        // 返回admin-page视图
        return "admin-page";

    }

    @RequestMapping(value = "/admin/do/logout.html")
    public String doLogout(HttpSession session){

        //强制Session失效
        session.invalidate();

        return "redirect:/admin/to/login/page.html";
    }

    @RequestMapping(value = "/admin/do/login.html")
    public String doLogin(
            @RequestParam("loginAcct") String loginAcct,
            @RequestParam("userPswd") String userPswd, HttpSession session){

        // 调用Service方法进行登录验证
        //这个方法如果能够返回admin对象说明登录成功，如果账号、密码不正确则抛出异常
        Admin admin = adminService.getAdminByLoginAcct(loginAcct,userPswd);

        //将成功登录的admin对象存入Session域
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);
        return "redirect:/admin/to/main/page.html";
    }

    @PreFilter(value = "filterObject%2==0")
    @ResponseBody
    @RequestMapping(value = "/admin/test/pre/filter.json")
    public ResultEntity<List<Integer>> saveList(@RequestBody List<Integer> valueList){

        return ResultEntity.successWithoutData(valueList);
    }

}
