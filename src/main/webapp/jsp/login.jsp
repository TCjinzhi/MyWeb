<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
	src="../bootstrap/js/jquery-2.0.0.min.js"></script>
<script type="text/javascript"
	src="../bootstrap/js/jquery-ui.js"></script>
<link
	href="../bootstrap/css/bootstrap-combined.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript"
	src="../bootstrap/js/bootstrap.min.js"></script>
<title>登录</title>
</head>
<body>
	<div class="container-fluid" id="LG">
		<div class="row-fluid">
			<div class="col-xs-6 span4"></div>
			<div class="col-xs-6 span4">
				<center>
					<h3>登录</h3>
				</center>
				<form class="form-horizontal" action="login" method="post">
					<c:if test='${not empty message}'>
						<div class="control-group" id="prompt">
							<center style="color:red" id="message"><%=request.getAttribute("message") %></center>
						</div>
					</c:if>
					<div class="control-group">
						<label class="control-label" for="userName">账号</label>
						<div class="controls">
							<input name="userName" type="text" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword">密码</label>
						<div class="controls">
							<input name="password" type="password" />
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<label class="checkbox"><input type="checkbox" /> 记住我</label>
							<button type="submit" class="btn">登陆</button>
							<br />
							<br /> <a href="../jsp/register.jsp">立即注册</a>
						</div>
					</div>
				</form>
			</div>
			<div class="col-xs-6 span4"></div>
		</div>
	</div>
</body>
</html>