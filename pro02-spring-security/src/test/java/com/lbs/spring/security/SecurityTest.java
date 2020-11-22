package com.lbs.spring.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Administrator
 * @date 2020/7/27 9:57
 * @description
 **/

// 带盐值对明文进行加密
public class SecurityTest {

    public static void main(String[] args){

        // 1.创建BCryptPasswordEncoder对象
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 2.对明文进行加密
        String rawPassword =  passwordEncoder.encode("123456");

        System.out.println(rawPassword);
        // $2a$10$bOr007PcreepuVan7XOE7OiiGF5Z023iwy1mxkZdouCPaSQ/F0.2C
        // $2a$10$Zv/HSQlNKBCpralWWx4q3.zBSrWw6kmjF0YtaqqdZLCGfD/aeJkKq
        // $2a$10$BuFHWRB.3U0Mis1QRloYMOAYkhDEOpmwZdB4tzVx.YSoAhmtc2gZy

    }

}


// 带盐值密文和明文进行比较
class EncodeTest{

    public static void main(String[] args){

        // 1.准备明文字符串
        String rawPassword = "123123";

        // 2.准备密文字符串
        String encodedPassword = "$2a$10$Zv/HSQlNKBCpralWWx4q3.zBSrWw6kmjF0YtaqqdZLCGfD/aeJkKq";

        // 3.创建BCryptPasswordEncoder对象
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 4.明文和密文进行比较
        boolean matchesResult =  passwordEncoder.matches(rawPassword,encodedPassword);

        System.out.println(matchesResult ? "一致" :"不一致");

    }

}
