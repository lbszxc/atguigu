package com.lbs.security.entity;

/**
 * @author Administrator
 * @date 2020/7/26 21:45
 * @description
 **/
public class Admin {
    private Integer id;
    private String loginacct;
    private String userpaswd;
    private String username;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginacct() {
        return loginacct;
    }

    public void setLoginacct(String loginacct) {
        this.loginacct = loginacct;
    }

    public String getUserpaswd() {
        return userpaswd;
    }

    public void setUserpaswd(String userpaswd) {
        this.userpaswd = userpaswd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
