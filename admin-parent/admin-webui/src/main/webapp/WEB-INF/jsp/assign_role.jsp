<%--
  Created by IntelliJ IDEA.
  User: 说好的幸福呢
  Date: 2020/6/17
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/include/include-head.jsp" %>
<script type="text/javascript">

    $(function () {

        // 点击去右边按钮
        $("#toRightBtn").click(function () {

            // select表示标签选择器
            // :eq(0)表示页面的第一个
            // :eq(1)表示页面的第二个
            // “>”表示选择子元素
            // :selected表示选择“被选中的”option
            // appendTo()能够将JQuery对象追加到指定位置
            $("select:eq(0)>option:selected").appendTo("select:eq(1)");

        });

        // 点击去左边按钮
        $("#toLeftBtn").click(function () {

            // select表示标签选择器
            // :eq(0)表示页面的第一个
            // :eq(1)表示页面的第二个
            // “>”表示选择子元素
            // :selected表示选择“被选中的”option
            // prependTo()能够将JQuery对象追加到指定位置前面
            $("select:eq(1)>option:selected").prependTo("select:eq(0)");

        });

        // 单击保存按钮选中已分配角色中的全部option
        $("#submitBtn").click(function () {

            $("select:eq(1)>option").prop("selected","selected");

        });

    });

</script>
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
                <li><a href="admin/get/page.htm">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="assign/do/role/assign.html" method="post" role="form" class="form-inline">
                        <input type="hidden" name="adminId" value="${param.adminId}"/>
                        <input type="hidden" name="pageNum" value="${param.pageNum}"/>
                        <input type="hidden" name="keyword" value="${param.keyword}"/>
                        <div class="form-group">
                            <label>未分配角色列表</label><br>
                            <select class="form-control" multiple="multiple" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.unAssignRoleList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="toLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label>已分配角色列表</label><br>
                            <select name="roleIdList" class="form-control" multiple="multiple" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.assignRoleList}" var="role">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button id="submitBtn" style="width: 10%;margin-left: 225px;margin-top: 10px" type="submit" class="btn btn-sm btn-success btn-block">保存</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
