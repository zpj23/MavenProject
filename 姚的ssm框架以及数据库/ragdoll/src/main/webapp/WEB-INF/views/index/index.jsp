<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function a(){
	$.post('/rest/rest2Service/getStr',function(data){
		alert(data);
	});
}

function b(){
	$.post('/rest/rest2Service/getUser',{"phone":"99999"},function(data){
		var jsonStr = JSON.stringify(data);
		alert(jsonStr);
	});
}

function c(){
	$.post('/rest/rest2Service/getUserList',{"phone":"99999"},function(data){
		var jsonStr = JSON.stringify(data);
		alert(jsonStr);
	});
}

</script>
 
</head>
<body>
<a href="/es/toSearch">测试</a> 


<button value="rest" onclick="a()">test string</button>

<button value="rest" onclick="b()">test2  entity</button>

<button value="rest" onclick="c()">test3 list</button>
 
</body>
</html>