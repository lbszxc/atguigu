package com.stillcrowdfunding.until;

import com.stillcrowdfunding.constant.CrowdConstant;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 * @date 2020/8/13 18:43
 * @description
 **/
public class AccessPassResources {

    public static final Set<String> PASS_RES_SET = new HashSet<>();

    static {

        // 去尚筹网首页的URL地址
        PASS_RES_SET.add("/");

        // 去注册的页面的URL地址
        PASS_RES_SET.add("/auth/member/to/reg/page");

        // 去登录的页面的URL地址
        PASS_RES_SET.add("/auth/member/to/login/page");

        // 执行退出登录的URL地址
        PASS_RES_SET.add("/auth/member/to/logout");

        // 执行登录的URL地址
        PASS_RES_SET.add("/auth/member/do/login");

        // 执行注册的URL地址
        PASS_RES_SET.add("/auto/member/do/register");

        // 执行发送验证码的URL地址
        PASS_RES_SET.add("/auto/member/send/short/message.json");

    }


    public static final Set<String> STATIC_RES_SET = new HashSet<>();

    // 放行静态资源
    static {

        STATIC_RES_SET.add("bootstrap");

        STATIC_RES_SET.add("css");

        STATIC_RES_SET.add("fonts");

        STATIC_RES_SET.add("img");

        STATIC_RES_SET.add("jquery");

        STATIC_RES_SET.add("layer");

        STATIC_RES_SET.add("script");

        STATIC_RES_SET.add("ztree");

    }

    /**
     * 用于判断某个ServletPath 值是否对应一个静态资源
     * @param servletPath
     * @return
     * true：是静态资源
     * false：不是静态资源
     */
    public static boolean judgeCurrentServletPathWetherStaticResource(String servletPath){

        // 1.排除字符串无效的情况
        if (servletPath == null || servletPath.length() == 0){

            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);

        }

        // 2.根据“/”拆分ServletPath 字符串
        String[] split =  servletPath.split("/");

        // 3.考虑到第一个斜杠左边经过拆分后得到一个空字符串是数组的第一个元素，所以需要使用下标1 取第二个元素
        String firstLevelPath = split[1];

        // 4.判断是否在集合中
        return STATIC_RES_SET.contains(firstLevelPath);


    }

    /*public static void main(String[] args) {

        String servletPath = "/css";

        boolean result = judgeCurrentServletPathWetherStaticResource(servletPath);

        System.out.println(result);

    }*/

}
