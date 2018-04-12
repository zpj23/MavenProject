
layui.use(['form','layer','jquery','laypage','table'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;
	var searchName="";
	//当前页
	var page=1;
	//每页限制条数
	var limit=10;
	//总数
	var totalNum=0;
	var table = layui.table;
	var ctable=table.render({
		    elem: '#t_userlist'
		    ,url:basePath+'userInfo/initList?page='+page+'&limit='+limit
		    ,where: {param: ''}
		    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
		    ,page: false //开启分页
		    ,cols: [[
		      {field:'id', type:'checkbox' }
		      ,{field:'loginName',minWidth:'50', title: '登录名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
		      ,{field:'name', minWidth:'50',title: '真实姓名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
		      ,{field:'sex', title: '性别',
		    	 templet: function(d){
		    		 var str="";
		    		 if(d.sex=="0"){
		    			 str='男';
		    		 }else if(d.sex=="1"){
		    			 str="女";
		    		 }else{
		    			 str="保密"
		    		 }
		    		 return str;
		    	  }
		      }
		      ,{field:'email', title: '电子邮箱'}
		      ,{field:'priority', title: '等级',
			    	 templet: function(d){
			    		 var str="";
			    		 if(d.priority=="0"){
			    			 str='注册用户';
			    		 }else if(d.priority=="1"){
			    			 str="中级用户";
			    		 }else if(d.priority=="2"){
			    			 str="中级用户"
			    		 }else if(d.priority=="3"){
			    			 str="高级用户"
			    		 }else if(d.priority=="4"){
			    			 str="超级用户"
			    		 }
			    		 return str;
			    	  }}
		      ,{field:'state', title: '状态',
			    	 templet: function(d){
			    		 var str="";
			    		 if(d.state=="0"){
			    			 str='正常';
			    		 }else if(d.priority=="1"){
			    			 str="禁用";
			    		 }else{
			    			 str="无";
			    		 }
			    		 return str;
			    	  }} 
		     
		    ]]
		    , done: function(res, curr, count){
		        //如果是异步请求数据方式，res即为你接口返回的信息。
		        //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
		        totalNum =count; 
		        initLayPage(); 	
		      }
		  });
	
	//分页信息构造
  	function initLayPage(){
  		laypage.render({
  	  	    elem: 'pageDiv'
  	  	    ,count: totalNum
  	  	    ,limit:limit
  	  	    ,curr:page
  	  	    ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
  	  	    ,jump: function(obj,first){
  	  	    	
	  	  	    if (!first) {
	  	  	    	page=obj.curr;
	  	  	    	limit=obj.limit;
	  	  	    	reloadTable();
	            }
  	  	    	
  	  	    }
  	  	  }); 	
  	}
  	function reloadTable(){
  		searchName=$("#searchName").val();
  		ctable.reload({
             where:{
              	param:searchName
             },
             url:basePath+'userInfo/initList?page='+page+'&limit='+limit
             ,page: false //开启分页
             
         });
  	}
	  	

	//查询
	$(".search_btn").click(function(){
		reloadTable();
	})

	//添加会员
	$(".usersAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加用户",
			type : 2,
			area: ['700px','600px'],
			content : basePath+"/userInfo/toAdd?id=",
			success : function(layero, index){
				layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
					tips: 3
				});
			}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
//		$(window).resize(function(){
//			layui.layer.full(index);
//		})
//		layui.layer.full(index);
	});
	


//	//操作
//	$("body").on("click",".users_edit",function(){  //编辑
//		layer.alert('您点击了会员编辑按钮，由于是纯静态页面，所以暂时不存在编辑内容，后期会添加，敬请谅解。。。',{icon:6, title:'文章编辑'});
//	})
	$("body").on("click",".batchDel",function(){  //删除
		var _this = $(this);
		layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
		      var checkStatus = table.checkStatus('t_userlist')  
		      ,data = checkStatus.data;  
		      console.log(data);
		      var ids="";
		      for(var i=0;i<data.length;i++){    //循环筛选出id  
		    	  if(i>0)
		    		  ids+=",";
		    	  ids+=data[i].id;
		      }  
		      $.ajax({
	                type: "POST",//方法类型
	                dataType: "json",//预期服务器返回的数据类型
	                url: basePath+"/userInfo/doDel?ids="+ids ,//url
	                success: function (result) {
	                    if (result.flag) {
	            			layer.msg("用户删除成功！",{time:3000});
	            	 		//刷新父页面
	            			reloadTable();
	                    }else{
	            			layer.msg("用户删除失败！",{time:3000});
	                    }
	                    
	                }
	            });
		       
			layer.close(index);
		});
	})

})