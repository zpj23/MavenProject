<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>    
    <title>totoro</title> 
<link rel="stylesheet" type="text/css" href="../css/theme1/default.css" />      
<!-- <script type="text/javascript" charset="utf-8" src="../ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="../ueditor1_4_3-utf8-jsp/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8" src="../ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="../ueditor1_4_3-utf8-jsp/lang/en/en.js"></script>
<script type="text/javascript" charset="utf-8" src="../common/file.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/messages_cn.js"></script>
<script type="text/javascript" src="../js/comm/common.js"></script> -->
	<script type="text/javascript">
	
	
   function save(){
	   form1.action = "doAdd";
	   form1.submit();
   }
	
	
	function toBack(){
	   form1.action = "toList";
	   form1.submit();
	}
	</script>
	
  </head>
  
  <body>
  
    <form name="form1" id="form1" method="post" class="">
      <table class="table1" width="100%" align="center">
         <tr>
           <th>用户名:</th>
           <td>
             <input type="text" class="input"  name="username"  value="${userinfo.username}" />
           </td>
         </tr>
         
         <tr>
           <th>密码:</th>
           <td>
             <input type="text" class="input"  name="pwd" id="pwd"  value="${userinfo.pwd}" />
           </td>
         </tr>
         
         <tr>
           <th>邮箱:</th>
           <td>
             <input type="text" class="input"  name="email" id="email"  value="${userinfo.email}" />
           </td>
         </tr>
         
         <tr>
           <th>手机:</th>
           <td>
             <input type="text" class="input" name="phone"  value="${userinfo.phone}" />
           </td>
         </tr>
                           
         <%-- <tr>
           <th>附件:</th>
           <td>             
             <div id="${uuid}" style="height:0px;width:32px;"></div>
             <input type="hidden" id="fileid" name="fileid" value="${uuid}" />
             <div id="FileList_${uuid}" ></div>             
             <script type="text/javascript">            
                   baidufile.init('edit','${uuid}','${uuid}');
             </script>
           </td>
         </tr> --%>
         
         <tr>
           <td colspan="2" align="center">
            <input type="hidden"  name="id"  id="id"  value="${userinfo.id}" />
            <input type="button" class="btn"  onclick="save();" value="保存" />
            <input type="button" class="btn" onclick="toBack();" value="返回" />	    	
           </td>
         </tr>
      </table>
      
    </form>
    
    
    
    
  </body>
</html>
