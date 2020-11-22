<%--
  Created by IntelliJ IDEA.
  User: 说好的幸福呢
  Date: 2020/6/17
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ include file="/WEB-INF/include/include-head.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">

<body>
<%@ include file="/WEB-INF/include/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="admin/to/main/page.html">首页</a></li>
                <li><a href="admin/get/page.html">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form action="admin/update.html" method="post"  role="form">
                        <input type="hidden" name="id" value="${requestScope.admin.id}"/>
                        <input type="hidden" name="pageNum" value="${param.pageNum}"/>
                        <input type="hidden" name="keyword" value="${param.keyword}"/>
                        <label style="color: red">${requestScope.exception.message}</label>
                        <div class="form-group">
                            <label for="exampleInputId">登录账号</label>
                            <input type="text" name="loginAcount" class="form-control" id="exampleInputId" value="${requestScope.admin.loginAcount}">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputName">用户昵称</label>
                            <input type="text" name="userName" class="form-control" id="exampleInputName" value="${requestScope.admin.userName}">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail">邮箱地址</label>
                            <input type="email" name="email" class="form-control" id="exampleInputEmail" value="${requestScope.admin.email}">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改</button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
