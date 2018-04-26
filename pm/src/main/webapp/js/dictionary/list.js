layui.config({
    base: basePath+"js/util/"      //自定义layui组件的目录
}).extend({ //设定组件别名
    common:   'common',
});
layui.use(['layer','jquery','tree','laypage','table','common'],function(){
	var layer = parent.layer === undefined ? layui.layer : parent.layer;
	var $ = layui.jquery;
	var laypage = layui.laypage;
	var table = layui.table;
	var common=layui.common;
	var searchName="";
	
});

