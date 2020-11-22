<%--
  Created by IntelliJ IDEA.
  User: 说好的幸福呢
  Date: 2020/6/16
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="keys" content="">
        <meta name="author" content="">
        <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/login.css">
        <script src="jquery/jquery-2.1.1.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <div><a class="navbar-brand" href="admin/to/main/page.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
                </div>
            </div>
        </nav>

        <div class="container">
            <form action="security/do/login.html" method="post" class="form-signin" role="form">
                <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 管理员登录</h2>
                <p>${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
                <div class="form-group has-success has-feedback">
                    <input name="loginAcct" value="jack" type="text" class="form-control" placeholder="请输入登录账号" autofocus>
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                </div>
                <div class="form-group has-success has-feedback">
                    <input name="userPswd" value="123456" type="text" class="form-control" placeholder="请输入登录密码" style="margin-top:10px;">
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>
                <button type="submit" class="btn btn-lg btn-success btn-block"> 登录</button>
            </form>
        </div>
    </body>
</html>

