<%@page import="com.zpj.sys.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
User USERINFO=(User)request.getSession().getAttribute("jluser");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
	<title>首页</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="favicon.ico">
	<link rel="stylesheet" href="${ctx}/plugin/layui/css/layui.css">
	<link rel="stylesheet" href="${ctx}/plugin/layui/css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="${ctx}/plugin/layui/css/main.css" media="all" />
<script type="text/javascript">
var basePath="<%=basePath%>";
var USERINFO="<%=USERINFO%>";
</script>	
</head>
<body class="main_body">
	<div class="layui-layout layui-layout-admin">
		<!-- 顶部 -->
		<div class="layui-header header">
			<div class="layui-main">
				<a href="#" class="logo">商品后台管理</a>
				<!-- 搜索 -->
				<div class="layui-form component">
			        <select name="modules" lay-verify="required" lay-search="">
						<option value="">直接选择或搜索选择</option>
						<option value="1">layer</option>
						<option value="2">form</option>
						<option value="3">layim</option>
						<option value="4">element</option>
						<option value="5">laytpl</option>
						<option value="6">upload</option>
						<option value="7">laydate</option>
						<option value="8">laypage</option>
						<option value="9">flow</option>
						<option value="10">util</option>
						<option value="11">code</option>
						<option value="12">tree</option>
						<option value="13">layedit</option>
						<option value="14">nav</option>
						<option value="15">tab</option>
						<option value="16">table</option>
						<option value="17">select</option>
						<option value="18">checkbox</option>
						<option value="19">switch</option>
						<option value="20">radio</option>
			        </select>
			        <i class="layui-icon">&#xe615;</i>
			    </div>
			    
			    <!-- 顶部右侧菜单 -->
			    <ul class="layui-nav top_menu">
			    	<li class="layui-nav-item " pc>
			    	<!-- 天气信息 -->
			    	<div  class="weather" >
	                    <div id="tp-weather-widget"></div>
						<script>(function(T,h,i,n,k,P,a,g,e){g=function(){P=h.createElement(i);a=h.getElementsByTagName(i)[0];P.src=k;P.charset="utf-8";P.async=1;a.parentNode.insertBefore(P,a)};T["ThinkPageWeatherWidgetObject"]=n;T[n]||(T[n]=function(){(T[n].q=T[n].q||[]).push(arguments)});T[n].l=+new Date();if(T.attachEvent){T.attachEvent("onload",g)}else{T.addEventListener("load",g,false)}}(window,document,"script","tpwidget","//widget.seniverse.com/widget/chameleon.js"))</script>
					<script>tpwidget("init", {
					    "flavor": "bubble",
					    "location": "WX4FBXXFKE4F",
					    "geolocation": "enabled",
					    "position": "top-left",
					    "margin": "5px 500px",
					    "language": "zh-chs",
					    "unit": "c",
					    "theme": "black",
					    "uid": "UEFEB51A89",
					    "hash": "0119bfea9a2f049d652a1ce51dc4f3ea"
					});
					tpwidget("show");</script>
					</div>
			    	</li>
			    	<li class="layui-nav-item showNotice" id="showNotice" pc>
						<a href="javascript:;"><i class="iconfont icon-gonggao"></i><cite>系统公告</cite></a>
					</li>
			    	<li class="layui-nav-item" mobile>
			    		<a href="javascript:;" data-url="page/user/changePwd.html"><i class="iconfont icon-shezhi1" data-icon="icon-shezhi1"></i><cite>设置</cite></a>
			    	</li>
			    	<li class="layui-nav-item" mobile>
			    		<a href="javascript:;"><i class="iconfont icon-loginout"></i> 退出</a>
			    	</li>
					<li class="layui-nav-item lockcms" pc>
						<a href="javascript:;"><i class="iconfont icon-lock1"></i><cite>锁屏</cite></a>
					</li>
					<li class="layui-nav-item" pc>
						<a href="javascript:;">
							<img src="${ctx}/plugin/layui/images/face.jpg" class="layui-circle" width="35" height="35">
							<cite>${USERINFO.name}</cite>
						</a>
						<dl class="layui-nav-child">
							<dd><a href="javascript:;" data-url="page/user/userInfo.html"><i class="iconfont icon-zhanghu" data-icon="icon-zhanghu"></i><cite>个人资料</cite></a></dd>
							<dd><a href="javascript:;" data-url="page/user/changePwd.html"><i class="iconfont icon-shezhi1" data-icon="icon-shezhi1"></i><cite>修改密码</cite></a></dd>
							<dd><a href="javascript:;"><i class="iconfont icon-loginout"></i><cite>退出</cite></a></dd>
						</dl>
					</li>
				</ul>
			</div>
		</div>
		<!-- 左侧导航 -->
		<div class="layui-side layui-bg-black">
			<div class="user-photo">
				<a class="img" title="我的头像" ><img src="${ctx}/plugin/layui/images/face.jpg"></a>
				<p>你好！<span class="userName">${USERINFO.name}</span>, 欢迎</p>
			</div>
			<div class="navBar layui-side-scroll"></div>
		</div>
		<!-- 右侧内容 -->
		<div class="layui-body layui-form">
			<div class="layui-tab marg0" lay-filter="bodyTab">
				<ul class="layui-tab-title top_tab">
					<li class="layui-this" lay-id=""><i class="iconfont icon-computer"></i> <cite>后台首页</cite></li>
				</ul>
				<div class="layui-tab-content clildFrame">
					<div class="layui-tab-item layui-show">
						<iframe src="${ctx}/home/toMain"></iframe>
					</div>
				</div>
			</div>
		</div>
		<!-- 底部 -->
		<div class="layui-footer footer">
			<p>copyright @2017 <a href="http://www.mycodes.net/" target="_blank">LayUI</a></p>
		</div>
	</div>

	<!-- 锁屏 -->
	<div class="admin-header-lock" id="lock-box" style="display: none;">
		<div class="admin-header-lock-img"><img src="${ctx}/plugin/layui/images/face.jpg"/></div>
		<div class="admin-header-lock-name" id="lockUserName">${USERINFO.name}</div>
		<div class="input_btn">
			<input type="password" class="admin-header-lock-input layui-input" placeholder="请输入密码解锁.." name="lockPwd" id="lockPwd" />
			<button class="layui-btn" id="unlock">解锁</button>
		</div>
		<p>请输入密码，否则不会解锁成功哦！！！</p>
	</div>
	<!-- 移动导航 -->
	<div class="site-tree-mobile layui-hide"><i class="layui-icon">&#xe602;</i></div>
	<div class="site-mobile-shade"></div>
	<script type="text/javascript" src="${ctx}/plugin/layui/layui.js"></script>
	<script type="text/javascript" src="${ctx}/js/home/index.js">

</script>
</body>
</html>