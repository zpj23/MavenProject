<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新闻搜索</title>
    <link type="text/css" rel="stylesheet" href="css/index.css">
</head>
<body>
<div class="box">
    <h1>Elasticsearch新闻搜索</h1>
    <div class="searchbox">
        <form action="/es/SearchNews" method="post">
            <input type="text" name="word">
            <input type="submit" value="搜索一下">
        </form>
    </div>
</div>
</body>
</html>
