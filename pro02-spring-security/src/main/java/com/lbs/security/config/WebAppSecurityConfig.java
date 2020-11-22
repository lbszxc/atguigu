package com.lbs.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * 注意！这个类一定要放在自动扫描的包下，否则所有配置都不会生效！
 * @author Administrator
 * @date 2020/7/25 16:57
 * @description
 **/

// 将当前类设置为一个配置类
@Configuration
// 启用Web环境下权限控制的功能
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyUserDetailService userDetailService;

    // 装配没有带盐值进行加密的密文
    //@Autowired
    //private MyPasswordEncoder passwordEncoder;

    // 装配带盐值进行加密的密文
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    // 每次调用这个方法时都会检查IOC容器中有对应的bean，如果有就不会执行这个函数，因为bean是单例的
    // 可以使用@Scope(value = "")这个注解控制是否是单例
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){

        return  new BCryptPasswordEncoder();
    }

    // 与SpringSecurity环境下用户登录相关
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        // 基于内存当中
       /* builder
            .inMemoryAuthentication()               //在内存中完成账号、密码的检查
            .withUser("jack")           //指定用户
            .password("123456")                   //指定用户密码
            .roles("ADMIN","学徒")                      //指定当前用户的角色
            .and()
            .withUser("tom")          //指定用户
            .password("123456")                  //指定用户密码
            .authorities("UPDATE","内门弟子")              // 指定当前用户的权限
        ;*/

       // 装配userDetailService对象
       builder
            .userDetailsService(userDetailService).passwordEncoder(getBCryptPasswordEncoder());
    }

    // 与SpringSecurity环境下请求授权相关
    @Override
    protected void configure(HttpSecurity security) throws Exception {

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(true);
        //tokenRepository.initDao();

        security
                .authorizeRequests()                            // 对请求授权
                .antMatchers("/index.jsp")      // 针对/index.jsp路径进行授权
                .permitAll()                                   // 可以无条件访问
                .antMatchers("/layui/**")       // 针对/layui目录下所有资源路径进行授权
                .permitAll()                                   // 可以无条件访问
                .antMatchers("/level1/**")       // 针对/level1/**路径设置访问要求
                .hasRole("学徒")                               // 要求用户具备学徒的角色才可以访问
                .antMatchers("/level2/**")
                .hasAuthority("内门弟子")                      // 要求用户具备“内门弟子”的权限才可以访问
                .antMatchers("/level3/**")
                .permitAll()
                .and()
                .authorizeRequests()                           // 对请求授权
                .anyRequest()                                  // 任意请求
                .authenticated()                               //  登录以后才能访问
                .and()
                .formLogin()                                   // 使用表单形式登录
                // 关于loginPage()方法的特殊说明
                // 指定登录页地址同时会影响到：“提交表单地址”、“退出登录地址”、“登录失败地址”
                //index.jsp GET - the login form        去登录页面
                //index.jsp POST - process the credentials and if valid authenticate the user       提交登录表单
                //index.jsp?error GET - redirect here for failed authentication attempts            登录失败
                //index.jsp?logout GET - redirect here after successfully logging out               退出登录
                .loginPage("/index.jsp")                      // 指定登录页面（如果没有指定登录页面，SpringSecurity会跳转到只带的登录页）
                // loginProcessingUrl()方法设定的地址就会覆盖loginPage()方法设置的默认地址/index.jsp
                .loginProcessingUrl("/do/login.html")        //  指定表单提交的地址
//                .permitAll()                                  //  登录地址本身也要permitAll()方法放行
                .usernameParameter("loginAcct")              // 登录账号请求参数
                .passwordParameter("userPaswd")              // 登录密码请求参数
                .defaultSuccessUrl("/main.html")             // 登录成功后跳转页面的地址
//                .and()
//                .csrf()
//                .disable()                                    // 禁用CSRF功能
                .and()
                .logout()                                    // 开启用户退出功能
                .logoutUrl("/do/logout.html")              // 指定处理退出请求的URL地址
                .logoutSuccessUrl("/index.jsp")            // 用户退出成功跳转页面的地址
                .and()
                .exceptionHandling()                        // 指定异常处理器
                .accessDeniedPage("/to/no/auth/page.html")      // 访问被拒绝跳转的页面
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                        httpServletRequest.setAttribute("message","对不起，您的等级不够访问该武功秘籍！");
                        httpServletRequest.getRequestDispatcher("/WEB-INF/views/no_auth.jsp").forward(httpServletRequest,httpServletResponse);
                    }
                })                  // 自己定制的要要跳转的页面
                .and()
                .rememberMe()                   // 开启记住我的功能
                .tokenRepository(tokenRepository)       // 装配token仓库

        ;

    }
}
