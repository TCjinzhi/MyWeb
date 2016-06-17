<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="../bootstrap/js/jquery-2.0.0.min.js"></script>
<script type="text/javascript" src="../bootstrap/js/jquery-ui.js"></script>
<link href="../bootstrap/css/bootstrap-combined.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<script type="text/javascript">
	function exportDoctorList() {//页面异步请求
	    var url = "../doctor/exportDoctorList";
	    window.open(url);
	};
	
</script>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="navbar">
					<div class="navbar-inner">
						<div class="container-fluid">
							<a data-target=".navbar-responsive-collapse"
								data-toggle="collapse" class="btn btn-navbar"><span
								class="icon-bar"></span><span class="icon-bar"></span><span
								class="icon-bar"></span></a> <a href="#" class="brand">网站名</a>
							<div class="nav-collapse collapse navbar-responsive-collapse">
								<ul class="nav">
									<li class="active"><a href="#">主页</a></li>
									<li><a href="#">链接</a></li>
									<li><a href="#">链接</a></li>
									<li class="dropdown"><a data-toggle="dropdown"
										class="dropdown-toggle" href="#">下拉菜单<strong class="caret"></strong></a>
										<ul class="dropdown-menu">
											<li><a href="#">下拉导航1</a></li>
											<li><a href="#">下拉导航2</a></li>
											<li><a href="#">其他</a></li>
											<li class="divider"></li>
											<li class="nav-header">标签</li>
											<li><a href="#">链接1</a></li>
											<li><a href="#">链接2</a></li>
										</ul></li>
								</ul>
								<ul class="nav pull-right">
									<li><a href="#">右边链接</a></li>
									<li class="divider-vertical"></li>
									<li class="dropdown"><a data-toggle="dropdown"
										class="dropdown-toggle" href="#">下拉菜单<strong class="caret"></strong></a>
										<ul class="dropdown-menu">
											<li><a href="#">下拉导航1</a></li>
											<li><a href="#">下拉导航2</a></li>
											<li><a href="#">其他</a></li>
											<li class="divider"></li>
											<li><a href="#">链接3</a></li>
										</ul></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="page-header">
					<h1>
						医生列表<small>信息查询</small>
					</h1>
				</div>
				<form class="form-search" >
					<input class="input-medium search-query" type="text" id="name" name="name" style="margin-left:60%"/>
					<button type="submit" class="btn">查找</button>
					<input type="button" value="导出" onclick="exportDoctorList();" class="btn"></input>
				</form>
				<table class="table table-striped table-hover table-condensed">
					<thead>
						<tr>
							<th>姓名</th>
							<th>手机</th>
							<th>级别</th>
							<th>省份</th>
							<th>城市</th>
							<th>医院</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${doctorList.list}" var="ara">
							<tr>
								<td><c:out value="${ara.name}" /></td>
								<td><c:out value="${ara.mobile}" /></td>
								<td><c:out value="${ara.level}" /></td>
								<td><c:out value="${ara.province}" /></td>
								<td><c:out value="${ara.city}" /></td>
								<td><c:out value="${ara.hospital}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination pagination-large pagination-centered">
					<ul>
						<li><a href="?action=firstPage">首页</a></li>
						<li><a href="?action=prePage">上一页</a></li>
						<li><a>当前第${doctorList.pageNum}页</a></li>
						<!--  <li><form action="../doctor/nextPage" method="post">
							<input class="btn btn-primary btn-large"
							type="submit" value="下一页"/>
						</form></li>-->
						<li><a href="?action=nextPage">下一页</a>
						<li><a href="?action=lastPage">尾页</a></li>
						<li><a>共 ${doctorList.pages} 页</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>