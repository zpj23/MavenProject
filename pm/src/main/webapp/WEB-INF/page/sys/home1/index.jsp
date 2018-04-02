<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
	<title>layui后台管理模板</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="favicon.ico">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">layui 后台布局</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          贤心
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">退了</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
  	<div class="navBar layui-side-scroll"></div>
<!--     <div class="layui-side-scroll"> -->
<!--       左侧导航区域（可配合layui已有的垂直导航） -->
<!--       <ul class="layui-nav layui-nav-tree"  lay-filter="test"> -->
<!--         <li class="layui-nav-item layui-nav-itemed"> -->
<!--           <a class="" href="javascript:;">所有商品</a> -->
<!--           <dl class="layui-nav-child"> -->
<!--             <dd><a href="javascript:;">列表一</a></dd> -->
<!--             <dd><a href="javascript:;">列表二</a></dd> -->
<!--             <dd><a href="javascript:;">列表三</a></dd> -->
<!--             <dd><a href="">超链接</a></dd> -->
<!--           </dl> -->
<!--         </li> -->
<!--         <li class="layui-nav-item"> -->
<!--           <a href="javascript:;">解决方案</a> -->
<!--           <dl class="layui-nav-child"> -->
<!--             <dd><a href="javascript:;">列表一</a></dd> -->
<!--             <dd><a href="javascript:;">列表二</a></dd> -->
<!--             <dd><a href="">超链接</a></dd> -->
<!--           </dl> -->
<!--         </li> -->
<!--         <li class="layui-nav-item"><a href="">云市场</a></li> -->
<!--         <li class="layui-nav-item"><a href="">发布商品</a></li> -->
<!--       </ul> -->
<!--     </div> -->
  </div>
  
  <div class="layui-body layui-form">
    <!-- 内容主体区域 -->
    <div style="padding: 15px;">内容主体区域</div>
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
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
  </div>
</div>
<script type="text/javascript" src="${ctx}/js/home/index.js">

</script>
</body>
</html>