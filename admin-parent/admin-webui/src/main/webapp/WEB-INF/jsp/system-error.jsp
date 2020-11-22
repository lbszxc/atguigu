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
    <script type="text/javascript">
        $(function () {
            $("button").click(function () {
                //相当于浏览器的后退按钮
                window.history.back();
            });
        });
    </script>
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
            </div>
        </div>
    </nav>

    <div class="container">

            <h2 class="form-signin-heading" style="text-align: center">
                <i class="glyphicon glyphicon-log-in"></i> 尚筹网系统信息
            </h2>

            <!--
                requestScope对应存放的是request域数据的Map;
                requestScope.exception相当于request.getAttribute("exception");
                requestScope.exception.message相当于exception.getMessage();
            -->
            <h3 style="text-align: center;color: red">${requestScope.exception.message}</h3>
            <button style="width: 15%;margin: 20px auto 0px auto" class="btn btn-lg btn-success btn-block">点我返回上一步</button>
    </div>
</body>
</html>

