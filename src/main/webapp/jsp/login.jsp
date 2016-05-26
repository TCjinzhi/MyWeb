<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
	src="http://www.francescomalagrino.com/BootstrapPageGenerator/3/js/jquery-2.0.0.min.js"></script>
<script type="text/javascript"
	src="http://www.francescomalagrino.com/BootstrapPageGenerator/3/js/jquery-ui"></script>
<link
	href="http://www.francescomalagrino.com/BootstrapPageGenerator/3/css/bootstrap-combined.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript"
	src="http://www.francescomalagrino.com/BootstrapPageGenerator/3/js/bootstrap.min.js"></script>
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