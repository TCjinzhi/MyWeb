<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test verify code</title>
<script type="text/javascript" src="../bootstrap/js/jquery-2.0.0.min.js"></script>
</head>
<body>
	<input id="veryCode" name="veryCode" type="text" />
	<img id="imgObj" alt="" src="${pageContext.request.contextPath}/cerification/identity.html" />
	<a href="#" onclick="changeImg()">换一张</a>
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