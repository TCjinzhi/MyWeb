<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<title>注册</title>
</head>
<body>
	<script language="javascript">
		function checkUsername() {//页面异步请求
			var userName = $("#userName").val();
			if(userName == ""){
				alert("用户名不能为空");
				document.getElementById("userName").focus();
				return false;
			}
		    var mydata = '{"userName":"' + $('#userName').val() +'"}';
		    $.ajax({
		        type : 'POST',
		        contentType : 'application/json',
		        url : "../user/checkUsername",
		        processData : false,
		        dataType : 'json',
		        data : mydata,
		        success : function(data) {
		            alert(data.username+data.msg);
		            document.getElementById("message").style.display = "block";
		            if(data.code == 0)
		            	document.getElementById("message").innerHTML = "<center>"+data.username+data.msg+"</center>";
		            if(data.code == 1){
		            	document.getElementById("message").innerHTML = "<center style=\"color:red\">"+data.username+data.msg+"</center>";
						document.getElementById("userName").value = "";
						document.getElementById("userName").focus();
		            }
		        },
		        error : function() {
		            alert('出错了！');
		        }
		    });
		};
	</script>
	<div class="container-fluid" id="LG">
		<div class="row-fluid">
			<div class="col-xs-6 span4">
			</div>
			<div class="col-xs-6 span4">
				<center>
					<h3>注册</h3>
				</center>
				<form class="form-horizontal" action="../user/register" method="post">
					<div class="control-group">
						<label class="control-label" for="userName">账号</label>
						<div class="controls">
							<input id="userName" name="userName" type="text" onblur="checkUsername();"/>
						</div>
					</div>
					<div id="message" class="control-group" style="display:none">
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword">密码</label>
						<div class="controls">
							<input id="password" name="password" type="password" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword">重复密码</label>
						<div class="controls">
							<input id="rePassword" type="password" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="age">年龄</label>
						<div class="controls">
							<input name="age" type="text" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="address">地址</label>
						<div class="controls">
							<input name="address" type="text" />
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn">注册</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col-xs-6 span4"></div>
		</div>
	</div>
</body>
</html>