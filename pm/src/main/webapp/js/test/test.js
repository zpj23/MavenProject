//layui.config({
//	  base: '/js/test/' //你存放新模块的目录，注意，不是layui的模块目录
//}).use('test'); //加载入口

//一般直接写在一个js文件中
layui.use(['layer', 'form'], function(){
  var layer = layui.layer
  ,form = layui.form;
  layer.msg('你好，这边已经被调用了');
});