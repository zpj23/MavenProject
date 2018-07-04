<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
pageContext.setAttribute("basePath", basePath); 
%>
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
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.10.1.min.js"></script>
	<link rel="stylesheet" href="${ctx}/plugin/layui/css/layui.css" media="all" />
	<script type="text/javascript" src="${ctx}/plugin/layui/layui.all.js"></script>	
<%-- 	<script type="text/javascript" src="${ctx}/js/dictionary/list.js"></script> --%>
	<script type="text/javascript">
	var layer;
	var basePath="<%=basePath%>";
	$(document).ready(function(){
		layer =layui.layer;
	});
	//删除树节点
	function removeDep(id){
		$.ajax({
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: basePath+"dictionary/doDelDictionaryType?id="+id ,//url
            success: function (result) {
                if (result.flag) {
        			top.layer.msg("删除成功！");
        			toRefreshTree();
                }else{
//                	top.layer.close(index);
        			top.layer.msg("删除失败！");
                }
                
            },error: function (XMLHttpRequest, textStatus, errorThrown) {
            	console.log(textStatus);
            	console.log(XMLHttpRequest);
            	console.log(errorThrown);
            },beforeSend: function () {
            },
            complete: function () {
            }
        });
	}
		//编辑类型树节点，打开编辑页面
		function openTypeDialog(cpk,ppk){
			
			var currentLayer =layer.open({
				title:"分类设置",
				type : 2,//iframe层
				anim:1,
				area: ['600px','400px'],
				content :basePath+'dictionary/toSave?cid='+cpk+'&pid='+ppk,
				success : function(layero, index){
				}
			});
			
		}
		//刷新树
		function toRefreshTree(){
			document.getElementById("treeIframe").contentWindow.toRefreshTree();
		}
		//编码页面的类型下的编码列表展示
		function toListItem(id){
			document.getElementById("listInfo").src=basePath+"dictionary/toListItem?id="+id;
		}
	</script>
</head>
<body >
	
	<div style="padding: 10px;">
	 <div class="layui-row" >
	 	<div class="layui-col-md3">
<!-- 	 	<blockquote class="layui-elem-quote"> -->
<!-- 	 		<div class="layui-inline"> -->
<!-- 				<a class="layui-btn  usersAdd_btn"><i class="layui-icon">&#xe608;</i>添加</a> -->
<!-- 			</div> -->
<!-- 			<div class="layui-inline"> -->
<!-- 				<a class="layui-btn  usersEdit_btn"><i class="layui-icon">&#xe642;</i>编辑</a> -->
<!-- 			</div> -->
<!-- 			<div class="layui-inline"> -->
<!-- 				<a class="layui-btn batchDel"><i class="layui-icon">&#xe640;</i>删除</a> -->
<!-- 			</div> -->
<!-- 	 	</blockquote>	 -->
		<iframe id="treeIframe" src="${ctx}/dictionary/toTree" frameborder="1" height="550px"> </iframe>
	    </div>
	    <div class="layui-col-md9">
<!-- 	    	<blockquote class="layui-elem-quote"> -->
<!-- 						<div class="layui-inline"> -->
<!-- 							<a class="layui-btn  usersAdd_btn"><i class="layui-icon">&#xe608;</i>添加</a> -->
<!-- 						</div> -->
<!-- 						<div class="layui-inline"> -->
<!-- 							<a class="layui-btn  usersEdit_btn"><i class="layui-icon">&#xe642;</i>编辑</a> -->
<!-- 						</div> -->
<!-- 						<div class="layui-inline"> -->
<!-- 							<a class="layui-btn batchDel"><i class="layui-icon">&#xe640;</i>删除</a> -->
<!-- 						</div> -->
<!-- 			</blockquote> -->
			<div style="padding: 10px;">
				<iframe id="listInfo" src="" frameborder="1" height="550px" width="100%"> </iframe>
			</div>
	    </div>
	  </div>
	 </div> 
</body>
</html>