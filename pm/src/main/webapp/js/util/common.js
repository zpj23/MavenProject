/*
	@Author: 朱培军
	@Time: 2018-04
	@Tittle: 初始化下拉框和radio 的值
	@Description: select,radio的值
*/
layui.define(["element","jquery"],function(exports){
	var element = layui.element,
		$ = layui.jquery,
		common={
			initRadio:function(name,val){
				$("[name='"+name+"']").each(function(){
					if($(this).val() == val) {
		                $(this).attr("checked",true);
			        }else{
			            $(this).attr("checked",false);
			        }
					
				});
			},
			initSelect:function(id,val){
				$("#"+id+" option").each(function(){
			        if($(this).val() == val) {
			                $(this).attr("selected",true)
			        }else{
			                $(this).attr("selected",false)
			        }
				});
			},
			initArea:function(id,val){
				$("#"+id).html(val);
			},
			ajaxMethod:function(url,data,method,success){
				layer.msg("调用封装的ajax啦！",{time:1000});
				//ajax请求数据
		        $.ajax({
		            type: method,
		            url: url,
		            dataType : "json",
		            data: data,
		            timeout: 20000,
		            error: function (data) {
		                console.log(data);
		                layer.msg("请求失败！",{time:1000});
		            },
		            success: function (data) {
		                //console.log(data);
		                success?success(data):function(){};
		            }
		        });
			},
			layerShow:function(title,width,height,url,type,animate){
				//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）。 若你采用layer.open({type: 1})方式调用，则type为必填项（信息框除外）
				var currentLayer = layui.layer.open({
					title : title,
					type : type==undefined?2:type,
					anim:animate==undefined?1:animate,
					area: [width,hieght],
					content : url,
					success : function(layero, index){
					}
				});
				return currentLayer;
			},
			layerClose:function(currentLayer){
				top.layer.close(currentLayer);
			}
		};
		
	exports("common",common);
})