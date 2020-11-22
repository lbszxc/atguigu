package com.stillcrowdfunding.mvc.config;

import com.stillcrowdfunding.constant.CrowdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * @date 2020/7/27 11:37
 * @description
 **/
// 表示当前类是一个配置类
@Configuration

// 启用WEB环境下的权限控制功能
@EnableWebSecurity

// 启用全局方法权限控制功能，并且设置prePostEnabled = true；
// 保证@PreAuthorize、@PreFilter、@PostAuthorize、@PostFilter生效
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CrowdUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 在这里声明就无法在XxxService中装配了
     * @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){

        return new BCryptPasswordEncoder();

    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {

        // 临时使用内存版登录模式测试代码
       /* builder
            .inMemoryAuthentication()
            .withUser("jack")
            .password("123456")
            .roles("管理员")
        ;*/

       // 正式功能中使用基于数据库的认证
        builder
            .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security
            .authorizeRequests()                                             //对请求进行授权
            .antMatchers("/admin/to/login/page.html")        //针对登录进行设置
            .permitAll()                                                    //无条件访问
            .antMatchers("/bootstrap/**")                    //针对静态资源进行设置
            .permitAll()                                                   //无条件访问
            .antMatchers("/crowd/**")                        //针对静态资源进行设置
            .permitAll()                                                   //无条件访问
            .antMatchers("/css/**")                          //针对静态资源进行设置
            .permitAll()                                                   //无条件访问
            .antMatchers("/fonts/**")                        //针对静态资源进行设置
            .permitAll()                                                   //无条件访问
            .antMatchers("/jquery/**")                       //针对静态资源进行设置
            .permitAll()                                                   //无条件访问
            .antMatchers("/layer/**")                        // 针对静态资源进行设置
            .permitAll()                                                   //无条件访问
            .antMatchers("/script/**")                       //针对静态资源进行设置
            .permitAll()                                                   //无条件访问
            .antMatchers("/ztree/**")                        //针对静态资源进行设置
            .permitAll()                                                   //无条件访问
            .antMatchers("/admin/get/page.html")          //针对分页显示Admin设定的访问控制
            //.hasRole("经理")                                             //要求具备经理角色
            .access("hasRole('经理') or hasAuthority('user:get')")   //要求具备“经理”角色和“user:get”权限二者之一
            .anyRequest()                                                 //其他任意的请求
            .authenticated()                                              //认证后访问
            .and()
            .exceptionHandling()
            .accessDeniedHandler(new AccessDeniedHandler() {
                @Override
                public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        request.setAttribute("exception",new Exception(CrowdConstant.MESSAGE_ACCESS_DENIED));
                        request.getRequestDispatcher("/WEB-INF/jsp/system-error.jsp").forward(request,response);
                }
            })
            .and()
            .csrf()                                                      //防跨站请求伪造功能
            .disable()                                                   //禁用
            .formLogin()                                                 //开启表单登录功能
            .loginPage("/admin/to/login/page.html")                   //指定登录页面
            .loginProcessingUrl("/security/do/login.html")            //指定处理登录请求的URL地址
            .usernameParameter("loginAcct")                            //用户密码请求参数
            .passwordParameter("userPswd")                             //用户登录请求参数
            .defaultSuccessUrl("/admin/to/main/page.html")           //指定登录成功后跳转页面的URL地址
            .and()
            .logout()                                                  //开启退出功能
            .logoutUrl("/security/do/logout.html")                  //指定处理退出请求的URL地址
            .logoutSuccessUrl("/admin/to/login/page.html")         //指定登录成功后跳转页面的URL地址
        ;
    }
}
