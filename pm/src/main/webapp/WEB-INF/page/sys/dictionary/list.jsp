<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>字典页面</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<meta name="renderer" content="webkit">
	<script type="text/javascript" src="${ctx}/js/dictionary/list.js"></script>
</head>
<body >
	
	<div style="padding: 10px;">
	 <div class="layui-row" >
	 	<div class="layui-col-md3">
	 	<blockquote class="layui-elem-quote">
	 		<div class="layui-inline">
				<a class="layui-btn  usersAdd_btn"><i class="layui-icon">&#xe608;</i>添加</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn  usersEdit_btn"><i class="layui-icon">&#xe642;</i>编辑</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn batchDel"><i class="layui-icon">&#xe640;</i>删除</a>
			</div>
	 	</blockquote>	
		<iframe id="treeIframe" src="${ctx}/dictionary/toTree" frameborder="1" height="500px"> </iframe>
	    </div>
	    <div class="layui-col-md9">
	    	<blockquote class="layui-elem-quote">
						<div class="layui-inline">
							<a class="layui-btn  usersAdd_btn"><i class="layui-icon">&#xe608;</i>添加</a>
						</div>
						<div class="layui-inline">
							<a class="layui-btn  usersEdit_btn"><i class="layui-icon">&#xe642;</i>编辑</a>
						</div>
						<div class="layui-inline">
							<a class="layui-btn batchDel"><i class="layui-icon">&#xe640;</i>删除</a>
						</div>
			</blockquote>
			<div style="padding: 10px;">
				<table class="layui-hide" id="t_userlist" lay-filter="t_userlist" ></table>
			</div>
	    </div>
	  </div>
	 </div> 
</body>
</html>