<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="http://www.francescomalagrino.com/BootstrapPageGenerator/3/js/jquery-2.0.0.min.js"></script>
<script type="text/javascript"
	src="http://www.francescomalagrino.com/BootstrapPageGenerator/3/js/jquery-ui"></script>
<link
	href="http://www.francescomalagrino.com/BootstrapPageGenerator/3/css/bootstrap-combined.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript"
	src="http://www.francescomalagrino.com/BootstrapPageGenerator/3/js/bootstrap.min.js"></script>
<link href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.css"
	rel="stylesheet">
<link href="../css/leftmenu.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
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
												class="dropdown-toggle" href="#">下拉菜单<strong
													class="caret"></strong></a>
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
												class="dropdown-toggle" href="#">下拉菜单<strong
													class="caret"></strong></a>
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
						<div class="row-fluid">
							<div class="row">
								<div class="span2">
									<ul id="main-nav" class="main-nav nav nav-tabs nav-stacked"
										style="">
										<li><a href="#"> <i
												class="glyphicon glyphicon-th-large"></i> 首页
										</a></li>
										<li><a href="#systemSetting" class="nav-header collapsed"
											data-toggle="collapse"> <i
												class="glyphicon glyphicon-cog"></i> 系统管理 <span
												class="pull-right glyphicon glyphicon-chevron-toggle"></span>
										</a>
											<ul id="systemSetting"
												class="nav nav-list secondmenu collapse"
												style="height: 0px;">
												<li><a href="#"><i class="glyphicon glyphicon-user"></i>&nbsp;用户管理</a></li>
												<li><a href="#"><i
														class="glyphicon glyphicon-th-list"></i>&nbsp;菜单管理</a></li>
												<li><a href="#"><i
														class="glyphicon glyphicon-asterisk"></i>&nbsp;角色管理</a></li>
												<li><a href="#"><i class="glyphicon glyphicon-edit"></i>&nbsp;修改密码</a></li>
												<li><a href="#"><i
														class="glyphicon glyphicon-eye-open"></i>&nbsp;日志查看</a></li>
											</ul></li>
										<li><a href="#configSetting" class="nav-header collapsed"
											data-toggle="collapse"> <i
												class="glyphicon glyphicon-cog"></i> 配置管理 <span
												class="pull-right glyphicon  glyphicon-chevron-toggle"></span>
										</a>
											<ul id="configSetting"
												class="nav nav-list secondmenu collapse in">
												<li><a href="#"><i
														class="glyphicon glyphicon-globe"></i>&nbsp;全局缺省配置</a></li>
												<li><a href="#"><i
														class="glyphicon glyphicon-star-empty"></i>&nbsp;未开通用户配置</a></li>
												<li><a href="#"><i class="glyphicon glyphicon-star"></i>&nbsp;退订用户配置</a></li>
												<li><a href="#"><i
														class="glyphicon glyphicon-text-width"></i>&nbsp;试用用户配置</a></li>
												<li><a href="#"><i
														class="glyphicon glyphicon-ok-circle"></i>&nbsp;开通用户配置</a></li>
											</ul></li>

										<li><a href="#disSetting" class="nav-header collapsed"
											data-toggle="collapse"> <i
												class="glyphicon glyphicon-globe"></i> 分发配置 <span
												class="pull-right glyphicon glyphicon-chevron-toggle"></span>
										</a>
											<ul id="disSetting" class="nav nav-list secondmenu collapse">
												<li><a href="#"><i
														class="glyphicon glyphicon-th-list"></i>&nbsp;分发包配置</a></li>
											</ul></li>

										<li><a href="#dicSetting" class="nav-header collapsed"
											data-toggle="collapse"> <i
												class="glyphicon glyphicon-bold"></i> 字典配置 <span
												class="pull-right glyphicon glyphicon-chevron-toggle"></span>
										</a>
											<ul id="dicSetting" class="nav nav-list secondmenu collapse">
												<li><a href="#"><i
														class="glyphicon glyphicon-text-width"></i>&nbsp;关键字配置</a></li>
											</ul></li>
										<li><a href="#"> <i class="glyphicon glyphicon-fire"></i>
												关于系统 <span class="badge pull-right">1</span>
										</a></li>

									</ul>
								</div>
								<div class="span10">这里是内容</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>