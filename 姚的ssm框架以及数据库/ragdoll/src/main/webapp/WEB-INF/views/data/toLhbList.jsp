<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>totoro</title>	
<script type="text/javascript">
		$().ready(function() {
	         $('#datagrid').datagrid({
				url : 'lhbDataJson',
				queryParams : {
					selectname : $("#selectname").val()
				},
				idField : 'id',
				frozenColumns : [ [ {
					title : 'id',
					field : 'id',
					width : 50,
					checkbox : true
				} ] ],
				columns : [ [  {
					field : 'name',
					title : '名称',
					width : 200
				},{
					field : 'code',
					title : '编码',
					width : 200
				},{
					field : 'ranges',
					title : '幅度',
					width : 200,
					formatter: function(value,row,index){
						return value+"%";
					}
				},{
					field : 'createtime',
					title : '创建时间',
					width : 100,
					formatter: function(value,row,index){
						return value.substring(0,10);
					}

				}]]
			});
		});
		
		function getdata(){			
			$.post("/data/gainLhbData",function(data){
				if(data==0){
					alert("数据抓取成功");
				}else{
					alert("数据抓取失败");
				}
			});			
		}
		
		
		function searchCat(){
			$('#datagrid').datagrid('load',{
				selectname : $("#selectname").val()
			});
		}
</script>

	</head>

	<body>
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',split:false,border:false">
				<div class="panel pd3" style="background-color: #E6EEF8;">
					<div class="panel-header">					    
					       <a class="easyui-linkbutton" iconCls="icon-add" id="tool_add"
							onclick="getdata();" plain="true" href="javascript:void(0);">获取数据</a>					   
					</div>
				</div>
			</div>
			<div data-options="region:'center',border:false"
				style="padding-top: 0px; background-color: #E6EEF8;" class="pd3">

				<form action="" method="post" id="form1">
					<div id="toolbar" class="datagrid-toolbar">
						<div class="wu-toolbar-search">

							<span> <label>
									名称：
								</label> <input name="selectname" id="selectname" /> </span>
							<a class="easyui-linkbutton" iconCls="icon-search" plain="true"
								id="tool_query" onclick="searchCat();"
								href="javascript:void(0);">开始检索</a>
						</div>
					</div>
				</form>

				<table id="datagrid" fit="true" fitColumns="true"
					style="height: auto; width: auto;" toolbar="#toolbar"
					 idField="id" border="true" rownumbers="false"
					singleSelect="false" pagination="true">
				</table>
			</div>
		</div>
	</body>
</html>
