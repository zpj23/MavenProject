<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>

<script type="text/javascript" src="../js/sys/home/home.js"></script>
<script type="text/javascript">
function configPortal(){
	window.frames["ContentBottomiframe_0"].location.href = "index";
}


var aa1;
$(function(){
	initUser();
	
	$("#left_tree").tree({
		onDblClick:function(){
			var nodes = $("#left_tree").tree('getSelected');
			if(nodes.state=="closed"){
				nodes.state=="open";
				$("#left_tree").tree("expand",nodes.target);
				$('#left_tree').tree("expandAll");
			}
			if(nodes.state=="open"){
				nodes.state=="closed";
				$("#left_tree").tree("collapseAll");
			}
			
	    }
	});
	
	/* show();
	var mytime = 1000*60*15;
	window.setInterval(show,mytime);
	 aa1  = $("#esWest").html();
	 
	 var width_ = $(".login_name").css("width");
	  width_ = width_.replace("px","")*1+162;
	 $(".loginout").css("right",width_); */
})


function initUser(){
		UserObj.userId = '${iuser.id}';
	    UserObj.userName = '${iuser.username}';
	 }

function fullScreen(){	   
		$("#mybody").layout("collapse","south");
		$("#mybody").layout("collapse","west");
		
		$("#a2").show();
		$("#a1").hide();
}

function fullScreen2(){	
		$("#mybody").layout("expand","south");
		$("#mybody").layout("expand","west");
		$("#a1").show();
		$("#a2").hide();	
}

function show() {
	var msg ="";		
	$.ajax({
		  url:"HnAction_remindMsg",
		  type:"post",
		  async:false,
		  success:function(data){
			  msg = data;
		  }
	});
	if(msg!=''){
		$.messager.show({  
	        title: '提示信息',  
	        msg: msg,
	        height:150,
	        timeout: 10000,  
	        showType: 'slide'  
	    });
	}
}


function remindurl(url){
	addTab("待办事项", url, null, true);
}


//更新数量
function reflushRedNum(){
	$.ajax({
		type:"post",
		url:"functionAction_reflushRedNum",
		success:function(obj){
			var ht=$("div[id^=num_]");
			console.log(ht);
			for(var i=0;i<ht.length;i++){
				var flag=false;
				for(var p in data){
					if(p==ht[i].id){
						flag=true;
						break;
					}
				}
				if(!flag){
					$("#"+ht[i].id).remove();
				}
			}
			var data= JSON.parse(obj);
			for(var p in data){
				if(str=="1"&&data[p]){
					
				}
				$("#"+p).html(data[p]);
			}
		}
		
	});
}


</script>
<style type="text/css">
body,button,input,select,textarea{font:12px/1.5 tahoma,arial,\5b8b\4f53;}

#left_tree li {
	margin: 0px;
	background: #EBEDEE url(${ctx}/images/index/b_j12.jpg) left bottom repeat-x;
	line-height: 32px;
	overflow: hidden;
}

.loginout{ position:absolute; right:234px; top:29px;}
</style>
</head>
<div id="jd_dialog" class="easyui-dialog"  
			data-options="modal:true,closed:true,iconCls:'icon-save'" 
				style="width:900px;height:500px;padding:10px;" >      
		</div>
		<div id="jd_dialog_window" class="easyui-dialog"  
			data-options="modal:true,closed:true,iconCls:'icon-save'" 
				style="width:1000px;height:600px;padding:10px;" >      
		</div> 
		<div class="la none">
          <ul>
            <a href="#" onclick="$('.la').hide();modifyPwd('${iuser.id}');return false"><li class="x_pass">修改密码</li></a>
            <a href="#" onclick="$('.la').hide();"><li class="x_ce">用户手册</li></a>
            <a href="#" onclick="loginout();return false"><li class="x_out">安全退出</li></a>
            <a href="FashionHomeAction_tofashionIndex" ><li class="x_cut">主页切换</li></a>
          </ul>
        </div>
<body class="easyui-layout" id="mybody" onload="bodyLast()">
	<!-- begin of header -->
	<div class="col_top" data-options="region:'north',border:false,split:false">
    	<div class="col_top">
 <div class="col_quick">
   <div class="col_banner">
     <a href="javascript:loginout();"  id="a2"><img src="../images/index/b_p9i.png"  class="loginout" border="0"/></a>
     <div class="login_name">
     	${iuser.username}
     </div>
     <a href="javascript:fullScreen();"  id="a1"><img src="../images/index/b_p9.png"  class="qie" border="0"/></a>
     <a href="" onclick="fullScreen2();return false;" id="a2" style="display: none"><img src="../images/index/b_p9.png"  class="qie" border="0"/></a>
     <input type="hidden" id="isfull"  value="0" />
     <span class="s_time"></span>
   </div>
 </div>
</div>
    </div>
    <!-- end of header -->
    <!-- begin of sidebar -->
	<div class="wu-sidebar" data-options="region:'west',split:true,title:'系统功能菜单',border:true,collapsible:true" id="esWest">
		<div class="easyui-accordion" data-options="border:false,fit:true" id="esAccordion" > 
			<c:forEach items="${userMenuList}" var="a" varStatus="vs">
				<c:if test="${a.parentid eq 1}">
					<div title="<span style='background:url(${a.picture}) no-repeat 0 0; padding-left:20px;'> ${a.name}</span>" >
						<ul id="left_tree" class="easyui-tree wu-side-tree" data-options="url:'../menu/showChildMenu?pid=${a.id}'" ></ul>						
					</div>
				</c:if>								
			</c:forEach> 
		</div>
    </div>	
    <!-- end of sidebar -->    
    <!-- begin of main -->
    <div class="wu-main" data-options="region:'center'">
        <div id="centerTab" class="easyui-tabs" data-options="border:false,fit:true">  
        </div>
    </div>
    <!-- end of main --> 
    <!-- begin of footer -->
	<div class="wu-footer" data-options="region:'south',border:true,split:false">
    	版权所有：for everyone
    </div>
    <!-- end of footer -->  
    <script type="text/javascript">
		$(function(){
			$('.wu-side-tree a').bind("click",function(){
				var title = $(this).text();
				var url = $(this).attr('data-link');
				var iconCls = $(this).attr('data-icon');
				var iframe = $(this).attr('iframe')==1?true:false;
				addTab(title,url,iconCls,iframe);
			});	
			
			Home.init();
			
			$('.login_name').hover(function(){
					 $('.la').show();
				}, function(){
					$('.la').hide();
			});
			$('.la').hover(function(){
					 $('.la').show();
				}, function(){
					$('.la').hide();
			});
			
		})
		
		/**
		* Name 载入树形菜单 
		*/
		$('#wu-side-tree').tree({
			url:'temp/menu.php',
			cache:false,
			onClick:function(node){
				var url = node.attributes['url'];
				if(url==null || url == ""){
					return false;
				}
				else{
					addTab(node.text, url, '', node.attributes['iframe']);
				}
			}
		});
		
		/**
		* Name 选项卡初始化
		*/
		$('#centerTab').tabs({
			tools:[{
				iconCls:'icon-application-home',
				title:'aaa',
				border:false,
				handler:function(){
					var tabPanel = $('#centerTab');
					tabPanel.tabs('select',"首页");
				}
			},{
				iconCls:'icon-application-view-tile',
				border:false,
				plain:true,
				handler:function(){
					var tabDom = CenterTab.getSelectTabWindow().panelIdArray;
					var columnNum_ = CenterTab.getSelectTabWindow().columnNum;
					columnNum_==1 ? columnNum_=2 : columnNum_=1;
					//alert(columnNum_);
					$.post('HomeAction_savePorlets', {columnNum:columnNum_, porlets:tabDom.join()},function(data){CenterTab.RefreshTab();});
				}
			},
			{
				iconCls:'icon-cross-octagon',
				title:'111',
				border:false,
				handler:function(){
					CenterTab.remove();
				}
			}]
		});
			
		/**
		* Name 添加菜单选项
		* Param title 名称
		* Param href 链接
		* Param iconCls 图标样式
		* Param iframe 链接跳转方式（true为iframe，false为href）
		*/	
		function addTab(title, href, iconCls, iframe){
			var tabPanel = $('#centerTab');
			//所有提醒共用一个tab，打开新的就把之前的关闭
			tabPanel.tabs("close", "待办事项");            
			if(!tabPanel.tabs('exists',title)){
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+ href +'" style="width:100%;height:100%;"  id="ContentBottomiframe_999999"></iframe>';
				if(iframe){
					tabPanel.tabs('add',{
						title:title,
						content:content,
						iconCls:iconCls,
						fit:true,
						closable:true
					});
				}
				else{
					tabPanel.tabs('add',{
						title:title,
						href:href,
						iconCls:iconCls,
						fit:true,
						closable:true
					});
				}
			}
			else
			{
				tabPanel.tabs('select',title);
			}
		}
		/**
		* Name 移除菜单选项
		*/
		function removeTab(){
			var tabPanel = $('#centerTab');
			var tab = tabPanel.tabs('getSelected');
			if (tab){
				var index = tabPanel.tabs('getTabIndex', tab);
				tabPanel.tabs('close', index);
			}
		}
		
		function loginout(){
			$.messager.confirm('询问', '您确定要退出吗？', function(b) {
				if (b) {
			      window.location.href="loginAction_cancellation";
				}
			});
			$('.la').hide();
		}
		
		function modifyPwd(userid){
// 			reflushRedNum();
			common.openWindow('修改密码', 'userAction_toModifyPwd?userid='+userid, 320, 200);
		}
		
		/**
		 * body加载后操作
		 */
		function bodyLast(){
			$('#left_tree').tree('expandAll');
		}
	</script>
</body>
</html>