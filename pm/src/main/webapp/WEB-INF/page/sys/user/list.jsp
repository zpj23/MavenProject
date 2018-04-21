<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>用户列表</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<meta name="renderer" content="webkit">
	
	<link rel="stylesheet" href="${ctx}/plugin/layui/css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="${ctx}/css/user/user.css" media="all" />
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
	
		<div class="layui-inline">
		    <div class="layui-input-inline">
		    	<input type="text" value="" id="searchName"  placeholder="请输入关键字" class="layui-input search_input">
		    </div>
		    <a class="layui-btn search_btn"><i class="layui-icon">&#xe615;</i>查询</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn  usersAdd_btn"><i class="layui-icon">&#xe608;</i>添加</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn  usersEdit_btn"><i class="layui-icon">&#xe608;</i>编辑</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn batchDel"><i class="layui-icon">&#xe640;</i>删除</a>
		</div>
	</blockquote>
	
	<div style="padding: 10px;">
		<table class="layui-hide" id="t_userlist" lay-filter="t_userlist" ></table>
	</div>
	<div style="position: fix;bottom: 10px;">
		<div id="pageDiv" ></div>
	</div>
	<script type="text/javascript" src="${ctx}/js/user/allUsers.js"></script>
</body>
</html>