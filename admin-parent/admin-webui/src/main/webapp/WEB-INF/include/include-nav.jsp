<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 控制面板</a></div>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li style="padding-top:8px;">
					<div class="btn-group">
						<button type="button" class="btn btn-default btn-success dropdown-toggle"
								data-toggle="dropdown">
							<i class="glyphicon glyphicon-user"></i>
							<!-- 通过页面才发现，principal用来就是我们自己封装的SecurityAdmin对象-->
							<%-- Principal：<security:authentication property="principal.class.name"/> --%>
							<!-- SecurityAdmin处理完登录操作之后把登录成功的User对象以principal属性名存入了UsernamePasswordAuthenticationToken对象 -->
							<!-- 通过访问当前对象的principal.originalAdmin.userName 属性可以获取用户的昵称-->
							<!--访问SecurityAdmin对象属性：-->
							<security:authentication property="principal.originalAdmin.userName"/>

							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
							<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
							<li class="divider"></li>
							<li><a href="security/do/logout.html"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
						</ul>
					</div>
				</li>
				<li style="margin-left:10px;padding-top:8px;">
					<button type="button" class="btn btn-default btn-danger">
						<span class="glyphicon glyphicon-question-sign"></span> 帮助
					</button>
				</li>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="查询">
			</form>
		</div>
	</div>
</nav>