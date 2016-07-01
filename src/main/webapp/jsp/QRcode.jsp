<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/amazeui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
<title>二维码</title>
</head>
<body>
	<div class="am-input-group">
		<input type="text" class="am-form-field" id="doc-qr-text"> <span
			class="am-input-group-btn">
			<button class="am-btn am-btn-default" type="button" id="doc-gen-qr">生成</button>
		</span>
	</div>
	<div id="doc-qrcode" class="am-text-center"></div>
	<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/amazeui.min.js"></script>
	<script>
		$(function() {
			var $input = $('#doc-qr-text');
			$qr = $('#doc-qrcode');

			function makeCode(text) {
				$qr.empty().qrcode(text);
			}

			$input.val(location.href);
			makeCode(location.href);

			$('#doc-gen-qr').on('click', function() {
				makeCode($input.val());
			});

			$input.on('focusout', function() {
				makeCode($input.val());
			});
		});
	</script>
</body>
</html>