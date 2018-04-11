
layui.use(['form','layer','jquery','laypage','table'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;
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
		  	//,request: {pageName:'currentPage',limitName: 'limitNum'}
		    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
		    ,page: false //开启分页
//		    ,limit:limitNum
		    ,cols: [[
		      {field:'id', title: 'ID', sort: true}
		      ,{field:'loginName', title: '登录名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
		      ,{field:'name', title: '真实姓名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
		      ,{field:'sex', title: '性别', sort: true}
		      ,{field:'email', title: '电子邮箱'}
		      ,{field:'priority', title: '等级'}
		      ,{field:'state', title: '状态', align: 'center'} //单元格内容水平居中
		     
		    ]]
		    , done: function(res, curr, count){
		        //如果是异步请求数据方式，res即为你接口返回的信息。
		        //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
//		        console.log(res);
//		        
//		        //得到当前页码
//		        console.log(curr); 
//		       
//		        //得到数据总量
//		        console.log(count);
		        
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
  		ctable.reload({
             where:{
              	param:''
             },
             url:basePath+'userInfo/initList?page='+page+'&limit='+limit
             ,page: false //开启分页
             
         });
  	}
	  	
	//加载页面数据
//	var usersData = '';
//	$.get(basePath+"plugin/layui/json/usersList.json", function(data){
//		usersData = data;
//		if(window.sessionStorage.getItem("addUser")){
//			var addUsers = window.sessionStorage.getItem("addUser");
//			usersData = JSON.parse(addUsers).concat(usersData);
//		}
//		//执行加载数据的方法
//		usersList();
//	})

	//查询
	$(".search_btn").click(function(){
//		var userArray = [];
//		if($(".search_input").val() != ''){
//			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
//            setTimeout(function(){
//            	
//            	$.ajax({
//					url : basePath+"plugin/layui/json/usersList.json",
//					type : "get",
//					dataType : "json",
//					success : function(data){
//						if(window.sessionStorage.getItem("addUsers")){
//							var addUsers = window.sessionStorage.getItem("addUsers");
//							usersData = JSON.parse(addUsers).concat(data);
//						}else{
//							usersData = data;
//						}
//						for(var i=0;i<usersData.length;i++){
//							var usersStr = usersData[i];
//							var selectStr = $(".search_input").val();
//		            		function changeStr(data){
//		            			var dataStr = '';
//		            			var showNum = data.split(eval("/"+selectStr+"/ig")).length - 1;
//		            			if(showNum > 1){
//									for (var j=0;j<showNum;j++) {
//		            					dataStr += data.split(eval("/"+selectStr+"/ig"))[j] + "<i style='color:#03c339;font-weight:bold;'>" + selectStr + "</i>";
//		            				}
//		            				dataStr += data.split(eval("/"+selectStr+"/ig"))[showNum];
//		            				return dataStr;
//		            			}else{
//		            				dataStr = data.split(eval("/"+selectStr+"/ig"))[0] + "<i style='color:#03c339;font-weight:bold;'>" + selectStr + "</i>" + data.split(eval("/"+selectStr+"/ig"))[1];
//		            				return dataStr;
//		            			}
//		            		}
//		            		//用户名
//		            		if(usersStr.userName.indexOf(selectStr) > -1){
//			            		usersStr["userName"] = changeStr(usersStr.userName);
//		            		}
//		            		//用户邮箱
//		            		if(usersStr.userEmail.indexOf(selectStr) > -1){
//			            		usersStr["userEmail"] = changeStr(usersStr.userEmail);
//		            		}
//		            		//性别
//		            		if(usersStr.userSex.indexOf(selectStr) > -1){
//			            		usersStr["userSex"] = changeStr(usersStr.userSex);
//		            		}
//		            		//会员等级
//		            		if(usersStr.userGrade.indexOf(selectStr) > -1){
//			            		usersStr["userGrade"] = changeStr(usersStr.userGrade);
//		            		}
//		            		if(usersStr.userName.indexOf(selectStr)>-1 || usersStr.userEmail.indexOf(selectStr)>-1 || usersStr.userSex.indexOf(selectStr)>-1 || usersStr.userGrade.indexOf(selectStr)>-1){
//		            			userArray.push(usersStr);
//		            		}
//		            	}
//		            	usersData = userArray;
//		            	usersList(usersData);
//					}
//				})
//            	
//                layer.close(index);
//            },2000);
//		}else{
//			layer.msg("请输入需要查询的内容");
//		}
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
	})

    //全选
	form.on('checkbox(allChoose)', function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		child.each(function(index, item){
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});

	//通过判断文章是否全部选中来确定全选按钮是否选中
	form.on("checkbox(choose)",function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})

	//操作
	$("body").on("click",".users_edit",function(){  //编辑
		layer.alert('您点击了会员编辑按钮，由于是纯静态页面，所以暂时不存在编辑内容，后期会添加，敬请谅解。。。',{icon:6, title:'文章编辑'});
	})

	$("body").on("click",".users_del",function(){  //删除
		var _this = $(this);
		layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
			//_this.parents("tr").remove();
			for(var i=0;i<usersData.length;i++){
				if(usersData[i].usersId == _this.attr("data-id")){
					usersData.splice(i,1);
					usersList(usersData);
				}
			}
			layer.close(index);
		});
	})

	function usersList(){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			currData = usersData.concat().splice(curr*nums-nums, nums);
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
					dataHtml += '<tr>'
			    	+  '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			    	+  '<td>'+currData[i].userName+'</td>'
			    	+  '<td>'+currData[i].userEmail+'</td>'
			    	+  '<td>'+currData[i].userSex+'</td>'
			    	+  '<td>'+currData[i].userGrade+'</td>'
			    	+  '<td>'+currData[i].userStatus+'</td>'
			    	+  '<td>'+currData[i].userEndTime+'</td>'
			    	+  '<td>'
					+    '<a class="layui-btn layui-btn-mini users_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
					+    '<a class="layui-btn layui-btn-danger layui-btn-mini users_del" data-id="'+data[i].usersId+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			        +  '</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
			}
		    return dataHtml;
		}

		//分页
//		var nums = 13; //每页出现的数据量
//		laypage.render({
//			elem : "page",
//			count : usersData.length,
//			jump : function(obj){
//				$(".users_content").html(renderDate(usersData,obj.curr));
//				$('.users_list thead input[type="checkbox"]').prop("checked",false);
//		    	form.render();
//			}
//		})
		
		
	}
        
})