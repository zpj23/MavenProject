layui.config({
    base: basePath+"js/util/"      //自定义layui组件的目录
}).extend({ //设定组件别名
    common:   'common',
});
layui.use(['form','layer','jquery','common'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer;
	var	$ = layui.jquery;
	var common=layui.common;
	setTimeout(() => {
		initData();  
		initSPFL('DPSP','fenlei1');
	}, 0);
	function initData(){
		common.ajaxMethod(basePath+"/supplierInfo/initSupplierSelect",{},"POST",
				function (result) {
                    if (result.flag) {
                    	var arr=result.list;
                    	var str="<option value='' selected>请选择</option>";
                    	for(var i=0;i<arr.length;i++){
                    		str+="<option value="+arr[i].id+">"+arr[i].name+"</option>";
                    	}
                    	$("#supplierId").append(str); 
                    	common.initSelect("supplierId",state);
                    }else{
            			layer.msg("初始化下拉框失败！",{time:3000});
                    }
                    //重新渲染样式
            		form.render(); 
            		
                });
		
	}
	//初始化商品分类
	function initSPFL(type,id){
		if(type==""||type==null||type==undefined){
			return;
		}
		common.ajaxMethod(basePath+"/goodsInfo/initFenlei",{"type":type},"POST",
				function (result) {
                    if (result.flag) {
                    	var arr=result.list;
                    	var str="";
                    	if(arr==null||arr.length==0){
                    		return;
                    	}
                    	for(var i=0;i<arr.length;i++){
                    		str+="<option value="+arr[i].typeCode+">"+arr[i].typeName+"</option>";
                    	}
                    	$("#"+id).html("<option value='' selected>请选择</option>");
                    	$("#"+id).append(str); 
                    }else{
            			layer.msg("初始化下拉框失败！",{time:3000});
                    }
                    var goodsType=$("#goodsType").val();
                    if(goodsType!=""){
                    	var arr=goodsType.split(",");
                    	if(id=="fenlei1"){
                        	$("#fenlei1").val(arr[0]);
                        	initSPFL(arr[0],'fenlei2');
                        }else if(id=="fenlei2"){
                        	$("#fenlei2").val(arr[1]);
                        	initSPFL(arr[1],'fenlei3');
                        }else if(id=="fenlei3"){
                        	$("#fenlei3").val(arr[2]);
                        }
                    }
                    //重新渲染样式
            		form.render(); 
                });
	}
	
	
	//监听select框
	form.on('select(supplierId)', function(data){
		$("#supplierName").val(data.elem[data.elem.selectedIndex].text);
	});
	
	form.on('select(fenlei1)', function(data){
		initSPFL(data.value,'fenlei2');
	});
	form.on('select(fenlei2)', function(data){
		initSPFL(data.value,'fenlei3');
	});
	form.on('select(fenlei3)', function(data){
		var gt=$("#fenlei1").val()+","+$("#fenlei2").val()+","+$("#fenlei3").val();
 		$("#goodsType").val(gt);
	});
 	var addUserArray = [],addUser;
 	form.on("submit(addInfo)",function(data){
 		$.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: basePath+"goodsInfo/doAdd" ,//url
                data: data.field,
                success: function (result) {
                    if (result.flag) {
            			top.layer.msg("保存成功！");
             			layer.closeAll("iframe");
            	 		//刷新父页面
            	 		parent.location.reload();
                    }else{
//                    	top.layer.close(index);
            			top.layer.msg("保存失败！");
                    }
                    
                },error: function (XMLHttpRequest, textStatus, errorThrown) {
                	console.log(textStatus);
                	console.log(XMLHttpRequest);
                	console.log(errorThrown);
                },beforeSend: function () {
//                	index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
//                	index=layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
                },
                complete: function () {
//                	 top.layer.close(index);
                }
            });
 		return false;
 	});
})

