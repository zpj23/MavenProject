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
	<link rel="stylesheet" href="${ctx}/plugin/layui/css/font_eolqem241z66flxr.css" media="all" />
	<link rel="stylesheet" href="${ctx}/css/user/user.css" media="all" />
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
	
		<div class="layui-inline">
		    <div class="layui-input-inline">
		    	<input type="text" value="" placeholder="请输入关键字" class="layui-input search_input">
		    </div>
		    <a class="layui-btn search_btn"><i class="layui-icon">&#xe615;</i>查询</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn  usersAdd_btn"><i class="layui-icon">&#xe608;</i>添加</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn batchDel"><i class="layui-icon">&#xe640;</i>删除</a>
		</div>
	</blockquote>
	<table class="layui-hide" id="t_userlist"></table>
<!-- 	<div class="layui-form news_list"> -->
<!-- 	  	<table class="layui-table"> -->
<!-- 		    <colgroup> -->
<!-- 				<col width="50"> -->
<!-- 				<col> -->
<!-- 				<col width="18%"> -->
<!-- 				<col width="8%"> -->
<!-- 				<col width="12%"> -->
<!-- 				<col width="12%"> -->
<!-- 				<col width="18%"> -->
<!-- 				<col width="15%"> -->
<!-- 		    </colgroup> -->
<!-- 		    <thead> -->
<!-- 				<tr> -->
<!-- 					<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th> -->
<!-- 					<th>登录名</th> -->
<!-- 					<th>邮箱</th> -->
<!-- 					<th>性别</th> -->
<!-- 					<th>会员等级</th> -->
<!-- 					<th>会员状态</th> -->
<!-- 					<th>最后登录时间</th> -->
<!-- 					<th>操作</th> -->
<!-- 				</tr>  -->
<!-- 		    </thead> -->
<!-- 		    <tbody class="users_content"></tbody> -->
<!-- 		</table> -->
<!-- 	</div> -->
	<div id="pageDiv"></div>
	<script type="text/javascript" src="${ctx}/js/user/allUsers.js"></script>
</body>
</html>