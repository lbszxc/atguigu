package com.lbs.crowd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Administrator
 * @date 2020/8/8 17:50
 * @description
 **/

@Configuration
public class CrowdWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        // 浏览器访问的地址
        String urlPath = "/auth/member/to/reg/page";

        // 目标视图的名称,将来拼接“prefix: classpath:/templates/”，“suffix: .html”前后缀
        String viewName = "member-reg";

        // 添加一个view-controller
        registry.addViewController(urlPath).setViewName(viewName);
        registry.addViewController("/auth/member/to/login/page").setViewName("member-login");
        registry.addViewController("/auth/member/to/center/page").setViewName("member-center");
        registry.addViewController("/member/my/crowd").setViewName("member-crowd");
    }
}
