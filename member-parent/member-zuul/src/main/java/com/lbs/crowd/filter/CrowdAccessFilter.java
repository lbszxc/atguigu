package com.lbs.crowd.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.stillcrowdfunding.constant.CrowdConstant;
import com.stillcrowdfunding.until.AccessPassResources;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Administrator
 * @date 2020/8/13 21:38
 * @description
 **/
@Component
public class CrowdAccessFilter extends ZuulFilter {
    @Override
    public String filterType() {

        // “pre”表示意思是在微服务启动前进行过滤
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {

        // 1.获取RequestContext对象
        RequestContext requestContext = RequestContext.getCurrentContext();

        // 2.通过RequestContext对象获取当前请求对象（框架底层是借助ThreadLocal从当前线程上获取事先绑定的Request对象）
        HttpServletRequest request =  requestContext.getRequest();

        // 3.获取servletPath值
        String servletPath = request.getServletPath();

        // 4.根据servletPath判断当前请求是否对应可以直接放行的特定功能
        boolean containsResult = AccessPassResources.PASS_RES_SET.contains(servletPath);

        if (containsResult){

            // 5.如果当前请求是可以直接放行的特定功能请求则返回false放行
            return false;

        }

        // 5.判断当前请求是否为静态资源
       // 工具方法返回true：说明当前请求是静态资源请求，取反为false 表示放行不做登录检查
      // 工具方法返回false：说明当前请求不是可以放行的特定请求也不是静态资源，取反为true表示需要做登录检查
        return !AccessPassResources.judgeCurrentServletPathWetherStaticResource(servletPath);
    }

    @Override
    public Object run() throws ZuulException {

        // 1.获取当前的请求对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        // 2.获取当前的Session对象
        HttpSession session = request.getSession();

        // 3.尝试从Session对象中获取已登录的用户
        Object loginMember = session.getAttribute(CrowdConstant.ATTR_NMAE_LOGIN_MEMBER);

        // 4.判断loginMember是否为空
        if (loginMember == null){

            // 5.从RequestContext对象中获取Response对象
            HttpServletResponse response = requestContext.getResponse();

            // 6.将提示信息存入Session域中
            session.setAttribute(CrowdConstant.ATTR_NAME_MESSAGE,CrowdConstant.MESSAGE_ACCESS_FORBIDDEN);

            // 7.重定向到member-authentication-consumer工程当中的登录页面
            try {
                response.sendRedirect("/auth/member/to/login/page");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }
}
