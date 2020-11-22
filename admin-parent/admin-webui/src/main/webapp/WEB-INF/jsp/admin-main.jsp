<%--
  Created by IntelliJ IDEA.
  User: 说好的幸福呢
  Date: 2020/6/17
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/include/include-head.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">


<body>
    <%@ include file="/WEB-INF/include/include-nav.jsp"%>
    <div class="container-fluid">
        <div class="row">
            <%@ include file="/WEB-INF/include/include-sidebar.jsp"%>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <h1 class="page-header">控制面板</h1>
                <%--
                <p>分析登录后显示用户昵称和用户密码擦除的测试代码：</p>
                Credentials：<security:authentication property="credentials"/><br/>
                Principal：<security:authentication property="principal.class.name"/><br/>
                访问SecurityAdmin对象loginAcount属性：<security:authentication property="principal.originalAdmin.loginAcount"/><br/>
                访问SecurityAdmin对象userPassword属性：<security:authentication property="principal.originalAdmin.userPassword"/><br/>
                访问SecurityAdmin对象userName属性：<security:authentication property="principal.originalAdmin.userName"/><br/>
                访问SecurityAdmin对象email属性：<security:authentication property="principal.originalAdmin.email"/><br/>
                --%>
                <div class="row placeholders">
                    <security:authorize access="hasRole('经理')">
                        <div class="col-xs-6 col-sm-3 placeholder">
                            <img data-src="holder.js/200x200/auto/sky" class="img-responsive" alt="Generic placeholder thumbnail">
                            <h4>Label</h4>
                            <span class="text-muted">Something else</span>
                        </div>
                    </security:authorize>
                    <security:authorize access="hasAuthority('role:delete')">
                        <div class="col-xs-6 col-sm-3 placeholder">
                            <img data-src="holder.js/200x200/auto/vine" class="img-responsive" alt="Generic placeholder thumbnail">
                            <h4>Label</h4>
                            <span class="text-muted">Something else</span>
                        </div>
                    </security:authorize>
                    <div class="col-xs-6 col-sm-3 placeholder">
                        <img data-src="holder.js/200x200/auto/sky" class="img-responsive" alt="Generic placeholder thumbnail">
                        <h4>Label</h4>
                        <span class="text-muted">Something else</span>
                    </div>
                    <div class="col-xs-6 col-sm-3 placeholder">
                        <img data-src="holder.js/200x200/auto/vine" class="img-responsive" alt="Generic placeholder thumbnail">
                        <h4>Label</h4>
                        <span class="text-muted">Something else</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
