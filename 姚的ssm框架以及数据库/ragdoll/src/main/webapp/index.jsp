<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新闻搜索</title>
    <link type="text/css" rel="stylesheet" href="css/index.css">
    <script type="text/javascript" src="../js/jquery-1.9.0.js"></script>
<script type="text/javascript">
function ab(){
	$.post('http://192.168.11.96:8088/vue/user/list',{
		"pageIndex":"1",
		"pageSize":"10",
		"condition":""
	},function (data){
		alert(data);
		console.log(data);
	});
  }

function ab2(){
	$.post('http://192.168.11.96:8088/vue/user/list1',function (data){
		alert(data);
		console.log(data);
	});
}
</script>
</head>

<body>
<div class="box">
    <h1>Elasticsearch新闻搜索</h1>
    <div class="searchbox">
        <form action="/es/SearchNews.htmls" method="post">
            <input type="text" name="word">
            <input type="submit" value="搜索一下">
            
            
            <input type="button" value="test" onclick="ab();">
        </form>
    </div>
</div>
</body>
</html>
