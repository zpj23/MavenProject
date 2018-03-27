<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="../js/jquery-1.9.0.js"></script>	
<style type="text/css">
.list_spage a {
    border: 1px solid #CCDBE4;
    padding: 2px 8px;
    margin-left: 5px;
    color: rgb(0, 0, 204);
    text-decoration: none;    
}

.list_spage span {
    margin-left: 5px;    
}

.list_spage a.no_page {
    color: #DBE1E6;
}

.list_spage a.currt {
    border: 1px solid #fff;
    color: #333;
    font-weight: bold;
}

.list_spage input {
    width: 34px;
    border: 1px solid #707070;
    height: 20px;
    color: #333;
    box-sizing: border-box;
}

.list_spage a.tiao {
    background: #F0F0F0;
    color: #404040;
    border: 1px solid #CCCCCC;
}
</style>
<script type="text/javascript">

function search(page){
	
	$("#page").val(page);
	form1.submit();
}


function lastPage(){
	var page = $("#page").val();
	$("#page").val(page*1-1);
	form1.submit();
}

function nextPage(){
	var page = $("#page").val();
	$("#page").val(page*1+1);
	form1.submit();
}

function firstPage(){
	$("#page").val(1);
	form1.submit();
}

function endPage(){
	$("#page").val('${totalpage}');
	form1.submit();
}

function jumpPage(){
	var a = $("#page").val();
    //所填写的跳转页数超出实际页数时，按最大的页数计算
	if(a>'${totalpage}'){
		$("#page").val('${totalpage}');
	}	
	form1.submit();
}
</script>
</head>
<body>
<form action="/es/SearchNews" method="post" id="form1">
<div class="box">
    <h1>Elasticsearch新闻搜索</h1>
    <div class="searchbox">
        
            <input type="text" name="word"  value="${word}">
            <input type="submit" value="搜索一下">
    </div>

<hr>
 总数：${totalHits} 个      耗时：${totalTime } 毫秒
 <hr>
 <c:forEach var="a" items="${newslist}" varStatus="t">
  ${t.index+1}:${a.title} <br>
  ${a.content}<br>
  <a href="${a.url}">${a.url}</a><br><br>
 </c:forEach>

<div class="list_spage tr cbo mt20 p10 pb30 pt30" align="center">
	<a href="javascript:firstPage()" class="">首页</a>
	<a href="javascript:lastPage()" class="${page eq 1?"no_page":""}">上一页</a>
	
	<c:forEach begin="${beginpage}"  end="${endpage}" step="1" var="a">
	  <c:if test="${a eq page}">
	  <a  class="currt">${a}</a>
	  </c:if>
	  <c:if test="${a ne page}">
	  <a href="javascript:search(${a})" >${a}</a>
	  </c:if>
	</c:forEach>
	
	<a href="javascript:nextPage()" class="${page eq totalpage?"no_page":""} ">下一页</a>
	<a href="javascript:endPage()" class="">尾页</a>
	 <input type="text" name="page" id="page"  value="${page}" />
	<a href="javascript:jumpPage()" class="tiao">跳转</a>
</div>

</div>
</form>
</body>
</html>