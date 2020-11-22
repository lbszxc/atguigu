package com.stillcrowdfunding.mvc.config;

import com.stillcrowdfunding.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * 考虑到User对象中仅仅包含账号和密码，为了获取到原始的Admin对象，专门创建SecurityAdmin这个类对User类进行扩展
 * @author Administrator
 * @date 2020/7/28 8:35
 * @description
 **/
public class SecurityAdmin extends User {


    private static final long serialVersionUID = 1L;

    // 原始的Admin对象，包含了Admin类中的全部属性
    private Admin originalAdmin;

    public SecurityAdmin(
            Admin originalAdmin,    //待传入的Admin对象

            List<GrantedAuthority> authorities  //创建角色、权限信息的集合
    ){
        //调用父类的构造器
        super(originalAdmin.getLoginAcount(),originalAdmin.getUserPassword(),authorities);

        //给本类的this.originalAdmin赋值
        this.originalAdmin = originalAdmin;

        //将原始的Admin对象的密码擦除
        //擦除密码是在不影响登录认证的情况下，避免密码泄露，增加系统的安全性
        this.originalAdmin.setUserPassword(null);

    }

    //对外提供获取原始Admin对象的getXxx()方法
    public Admin getOriginalAdmin() {
        return originalAdmin;
    }
}
