<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../bootstrap/js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="../bootstrap/js/jquery-ui.js"></script>
<link href="../bootstrap/css/bootstrap-combined.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>
<title>登录</title>
</head>
<body>
	<div class="container-fluid" id="LG">
		<div class="row-fluid">
			<div class="col-xs-6 span4"></div>
			<div class="col-xs-6 span4">
				<h3 align="center">登录</h3>
				<form class="form-horizontal" action="../user/login" method="post">
					<c:if test='${not empty message}'>
						<div class="control-group" id="prompt">
							<p style="color: red" align="center" id="message"><%=request.getAttribute("message")%></p>
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
						<label class="control-label" for="inputPassword">验证码</label>
						<div class="controls">
							<input id="veryCode" name="veryCode" type="text" /> <img
								id="imgObj" alt=""
								src="${pageContext.request.contextPath}/cerification/identity" />
							<a href="#" onclick="changeImg()">换一张</a>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<label class="checkbox"><input type="checkbox" /> 记住我</label>
							<button type="submit" class="btn">登陆</button>
							<br /> <br /> <a href="../jsp/register.jsp">立即注册</a>
						</div>
					</div>
				</form>
			</div>
			<div class="col-xs-6 span4"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function changeImg() {
		var imgSrc = $("#imgObj");
		var src = imgSrc.attr("src");
		//alert(src);
		imgSrc.attr("src", chgUrl(src));
	}
	//时间戳   
	//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳   
	function chgUrl(url) {
		var timestamp = (new Date()).valueOf();
		//url = url.substring(0, 33);
		if ((url.indexOf("&") >= 0)) {
			url = url + "×tamp=" + timestamp;
		} else {
			url = url + "?timestamp=" + timestamp;
		}
		//alert(url);
		return url;
	}
</script>
</html>