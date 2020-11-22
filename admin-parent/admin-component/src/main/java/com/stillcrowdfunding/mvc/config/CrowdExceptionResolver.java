package com.stillcrowdfunding.mvc.config;

import com.google.gson.Gson;
import com.stillcrowdfunding.constant.CrowdConstant;
import com.stillcrowdfunding.exception.LoginAccountAlreadyInUseException;
import com.stillcrowdfunding.exception.LoginAccountAlreadyInUseForUpdateException;
import com.stillcrowdfunding.exception.LoginFailedException;
import com.stillcrowdfunding.until.CrowdUtil;
import com.stillcrowdfunding.until.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基于注解异常映射
 * @author Administrator
 * @date 2020/6/16 11:01
 * @description
 **/

// @ControllerAdvice表示当前是一个基于注解异常处理的类
@ControllerAdvice
public class CrowdExceptionResolver {

    // @ExceptionHandler将一个具体的异常类型和方法关联起来
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointerException(
            NullPointerException exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        // 1.设置对应的返回视图名称
        String viewName = "system-error";

        // 2.返回ModelAndView对象
        return commonResole(viewName,exception,request,response);
    }

    @ExceptionHandler(value = LoginAccountAlreadyInUseException.class)
    public ModelAndView resolveLoginAccountAlreadyInUseException(
            LoginAccountAlreadyInUseException exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException{

        String viewName = "admin-add";

        return commonResole(viewName,exception,request,response);
    }

    @ExceptionHandler(value = LoginAccountAlreadyInUseForUpdateException.class)
    public ModelAndView resolveLoginAccountAlreadyInUseForUpdateException(
            LoginAccountAlreadyInUseForUpdateException exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException{

        String viewName = "system-error";

        return commonResole(viewName,exception,request,response);
    }

    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(LoginFailedException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "admin-login";
        //返回
        return commonResole(viewName, exception, request, response);
    }

    @ExceptionHandler(value = ArithmeticException.class)
    public ModelAndView resolveMathException(ArithmeticException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String viewName = "system-error";
        //返回
        return commonResole(viewName, exception, request, response);
    }


    @ExceptionHandler(value = Exception.class)
    public ModelAndView resolveLoginFailedException(
            Exception exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException{

        String viewName = "system-error";

        return commonResole(viewName,exception,request,response);
    }


    private ModelAndView commonResole(
            /* 异常处理后要返回视图的名称 */ String ViewName,
            /* 实际捕获的异常类型 */ Exception exception,
            /* 当前请求的对象 */ HttpServletRequest request,
            /* 当前响应的对象 */ HttpServletResponse response) throws IOException {

        // 1.判断请求的类型
        boolean judgeRequest =  CrowdUtil.judgeRequestType(request);

        // 2.如果是Ajax请求
        if(judgeRequest){

            // 3.创建ResultEntity对象
            ResultEntity<Object> resultEntity =   ResultEntity.failed(exception.getMessage());

            // 4.创建Gson对象
            Gson gson = new Gson();

            // 5.将ResultEntity对象转换为JSON字符串
            String json = gson.toJson(resultEntity);

            // 6.将JSON字符串作为响应体返回给浏览器
            response.getWriter().write(json);

            // 7.由于上面已经通过原生态的response对象返回了响应，所以不提供ModelAndView对象
            return null;
        }

        // 8.如果不是Ajax请求则创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();

        // 9.将Exception对象存入模型
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION,exception);

        // 10.设置对应的返回视图名称
        modelAndView.setViewName(ViewName);

        // 11.返回ModelAndView对象
        return modelAndView;

    }
}
