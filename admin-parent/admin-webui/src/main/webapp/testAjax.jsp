<%--
  Created by IntelliJ IDEA.
  User: 说好的幸福呢
  Date: 2020/6/17
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ include file="/WEB-INF/include/include-head.jsp" %>
<script type="text/javascript">
    $(function () {
        $("#asyncBtn").click(function () {
            console.log("Ajax函数之前");
            $.ajax({
                url:"test/ajax/async.html",
                type:"post",
                dateType:"text",
                async:false,        // 关闭异步工作模式，使用同步方式工作。此时：所有操作在同一个线程内按顺序执行
                success:function (response) {

                    // success是接收到服务器端响后应执行
                    console.log("Ajax函数内部的success函数："+response);

                }
            });

            /*setTimeout(function () {
                // 在$.ajax()执行完成后执行，不等待success函数的执行
                console.log("Ajax函数之后");
            },5000)*/

            // 在$.ajax()执行完成后执行，不等待success函数的执行
            console.log("Ajax函数之后");

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
            <%--发送异步Ajax请求--%>
            <button id="asyncBtn">发送异步Ajax请求</button>
        </div>
    </div>
</div>
</body>
</html>
