<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx}js/jquery/jquery-1.10.1.min.js"></script> 
<script type="text/javascript">

	function uploadfile(){
		form2.action = "/file/doFile";
		form2.submit();
	}



</script>
</head>
<body>


 <form id="form1" action="/file/doFile"  method="post" enctype="multipart/form-data">
         上传用户：<input type="text" name="filename">  <input type="text" name="modulename"> <br/>
         上传文件1：<input type="file" name="file"><br/>
         <input type="submit" value="提交">
         
         
         path : ${path}
 </form>

<form name="form2" id="form2" method="post" action="" enctype="multipart/form-data">
     <div style="height: 50px;"></div>
     <div style="margin-left: 200px;">
      <input type="file" name="file" id="fileid" />
      <input type="hidden" name="linkid" value="aa111aa" />      
      <input type="button" value="上传附件" onclick="uploadfile()" />
     </div>
     
 </form>    

</body>
</html>