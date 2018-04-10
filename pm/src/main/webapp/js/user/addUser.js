var $;
layui.use(['form','layer','jquery'],function(){
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;

 	var addUserArray = [],addUser;
 	form.on("submit(addUser)",function(data){
// 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
	 	$.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: basePath+"/userInfo/doAdd" ,//url
                data: data.field,
                success: function (result) {
                    console.log(result);//打印服务端返回的数据(调试用)
                    if (result=="success") {
                    	//弹出loading
                        top.layer.close(index);
            			top.layer.msg("用户添加成功！");
             			layer.closeAll("iframe");
            	 		//刷新父页面
            	 		parent.location.reload();
                    }
                    
                },error:function(e){
                	alert(e);
                }
            });
 		return false;
 	});
//	function tijiao(){
//		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
//	 	$.ajax({
//                type: "POST",//方法类型
//                dataType: "json",//预期服务器返回的数据类型
//                url: basePath+"/userInfo/doAdd" ,//url
//                data: data.field,
//                success: function (result) {
//                    console.log(result);//打印服务端返回的数据(调试用)
//                    if (result=="success") {
//                    	//弹出loading
//                        top.layer.close(index);
//            			top.layer.msg("用户添加成功！");
//             			layer.closeAll("iframe");
//            	 		//刷新父页面
//            	 		parent.location.reload();
//                    }
//                    
//                },error:function(e){
//                	alert(e);
//                }
//            });
//	}
})

//格式化时间
function formatTime(_time){
    var year = _time.getFullYear();
    var month = _time.getMonth()+1<10 ? "0"+(_time.getMonth()+1) : _time.getMonth()+1;
    var day = _time.getDate()<10 ? "0"+_time.getDate() : _time.getDate();
    var hour = _time.getHours()<10 ? "0"+_time.getHours() : _time.getHours();
    var minute = _time.getMinutes()<10 ? "0"+_time.getMinutes() : _time.getMinutes();
    return year+"-"+month+"-"+day+" "+hour+":"+minute;
}
