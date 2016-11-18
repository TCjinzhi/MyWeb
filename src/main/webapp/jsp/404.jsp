<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>404</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/public.js"></script>
</head>
<body>
<div id="content" class="cf">
	<div id="duobei_wrap">
		<div class="logo_wrap cf">
			<a href="${pageContext.request.contextPath}/user/index" id="logo"></a>
			<div id="title"><i>信</i><i>息</i><i>系</i><i>统</i></div>
		</div>
		<div class="reason">
			<p class="not_found_tip">Not Found  :( 很抱歉，您访问的页面不存在!</p>
			<p class="possible">可能原因：</p>
			<ul>
				<li>服务器不稳定，请退回刷新重试</li>
				<li>您手动输入了一个从来没有的访问url，系统无法识别您的url</li>
			</ul>
		</div>
	</div>
	<!-- #duobei_wrap -->
	<div class="line"></div>

	<div class="not_found">
		<i class="ribbon"></i>
		<div class="not_found_404 cf">
			<span>4</span>
			<span>0</span>
			<span>4</span>
		</div>
		<div class="btn">
			<a href="${pageContext.request.contextPath}/user/index" class="button button-rounded">返回登录</a>
			<a href="#" class="button button-rounded cancle">返回上页</a>
		</div>   
	</div>
	<!-- not_found -->

</div>
</body>
</html>