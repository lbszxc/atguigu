package com.lbs.spring.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @date 2020/8/5 9:35
 * @description
 **/
@Component
public class MyZuulFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(MyZuulFilter.class);

    /**
     * 判断当前请求是否要进行过滤
     * 1.不要过滤：返回false，直接放行
     * 2.要过滤：返回true，继续执行run()方法
     * @return
     */

    public String filterType() {

        // 返回当前过滤器的类型，决定了当前过滤器在什么时候执行
        // pre表示当前过滤器在微服务前执行
        String filterType = "pre";

        return filterType;
    }

    public int filterOrder() {
        return 0;
    }

    public boolean shouldFilter() {

        // 1.获取RequestContext对象
        RequestContext requestContext = RequestContext.getCurrentContext();

        // 2.获取Request对象
        HttpServletRequest servletRequest = requestContext.getRequest();

        // 3.判断当前Request请求参数是否为signal=hello
        String parameter = servletRequest.getParameter("signal");

        return "hello".equals(parameter);
    }

    public Object run() throws ZuulException {

        logger.info("当前请求要进行过滤，run()方法已经执行了！");

        // 当前方法的实现会忽略这个方法的返回值，所以返回值为null，不做特殊处理
        return null;
    }
}
