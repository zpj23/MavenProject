<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>totoro</title>	
<script type="text/javascript">
		$().ready(function() {
	         $('#datagrid').datagrid({
				url : 'userData',
				queryParams : {
					selectname : $("#selectname").val()
				},
				idField : 'catid',
				frozenColumns : [ [ {
					title : 'id',
					field : 'id',
					width : 50,
					checkbox : true
				} ] ],
				columns : [ [  {
					field : 'username',
					title : '用户名',
					width : 200
				},{
					field : 'email',
					title : '邮箱',
					width : 200
				},{
					field : 'phone',
					title : '电话',
					width : 200
				},{
					field : 'createtime',
					title : '创建时间',
					width : 100
				}]]
			});
		});
		
		
		//新增
		function addCat(){
		   form1.action="toAdd";
		   form1.submit();
		}
		
		function searchCat(){
		   $('#datagrid').datagrid('load', {
			   selectname:$.trim($("#selectname").val())	
		   });
		}
		
		
		function editCat(){
		   var rowData =  $('#datagrid').datagrid('getSelections');
			var num = rowData.length;
			if (num == 0) {
				alert('请选择要编辑的记录!');
			}
			if (num > 1) {
				alert('只能择一个记录进行编辑!');
			}
			if (num == 1) {
				form1.action = "toAdd?id="+rowData[0].id;
				form1.submit();
			}
		
		}
		
		function viewCat(){
		   var rowData =  $('#datagrid').datagrid('getSelections');
			var num = rowData.length;			
			if (num == 0) {
				alert('请选择要查看的记录!');
			}
			if (num > 1) {
				alert('只能择一个记录进行查看!');
			}
			if (num == 1) {
				form1.action = "toViewCat.c?catid="+rowData[0].catid;
				form1.submit();
			}		
		}
		
		
		function delCat(){
		    var rowData =  $('#datagrid').datagrid('getSelections');
			var num = rowData.length;		
			if (num == 0) {
				$.messager.alert('提示','请选择要删除的记录!','info');
			} else {
				$.messager.confirm('询问','您要删除当前所选记录？',function (b){
					if (b) {
					    var rowData =  $('#datagrid').datagrid('getSelections');
					    var id='';
					    for(var i=0;i<rowData.length;i++){
					       id += rowData[i].catid+',';
					    }
						id = id.substring(0, id.length - 1);
						$('#form1').form('submit',
							{
								url : "doDelCat.c?ids=" + id,
								onSubmit : function() {
								},
								success : function(data) {
								    if(data==0){
							            alert('删除成功');
							         }else{
							            alert('删除失败');
							         }
									$('#datagrid').datagrid("reload");
								}
							});	
				}
			});
				
		 }
		}
		
		
		function pageCat(){
			form1.action = "list.c";
			form1.submit();
		}
</script>

	</head>

	<body>
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',split:false,border:false">
				<div class="panel pd3" style="background-color: #E6EEF8;">
					<div class="panel-header">
					    <shiro:hasPermission name="cat:add">
					       <a class="easyui-linkbutton" iconCls="icon-add" id="tool_add"
							onclick="addCat();" plain="true" href="javascript:void(0);">新增</a>	
					    </shiro:hasPermission>
						
						<shiro:hasPermission name="cat:update">		
						<a class="easyui-linkbutton" iconCls="icon-edit" id="tool_update"
							onclick="editCat();" plain="true" href="javascript:void(0);">编辑</a>
						</shiro:hasPermission>
						
						<shiro:hasPermission name="cat:update">
						<a class="easyui-linkbutton" iconCls="icon-remove" id="tool_del"
							onclick="delCat();" plain="true" href="javascript:void(0);">删除</a>
						</shiro:hasPermission>
						
						<shiro:hasPermission name="cat:view">		
						<a class="easyui-linkbutton"
							iconCls="icon-application-view-detail" id="tool_view"
							onclick="viewCat()" plain="true" href="javascript:void(0);">查看</a>
						</shiro:hasPermission>	
							
						<shiro:hasPermission name="cat:query">	
						<a class="easyui-linkbutton"
							iconCls="icon-application-view-detail" id="tool_view"
							onclick="pageCat()" plain="true" href="javascript:void(0);">分页</a>
						</shiro:hasPermission>	
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
