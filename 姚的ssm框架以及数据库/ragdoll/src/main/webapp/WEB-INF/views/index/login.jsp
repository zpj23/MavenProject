<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>重大决策社会稳定风险评估管理平台</title>
<style>
body{ background:#0B5D97;font:12px/1.5 tahoma,arial,\5b8b\4f53;}

input[type=text], input[type=password] { border: 0px #95b8e7 solid;}
</style>
<script type="text/javascript">
function checkLogin() {
	form1.action = "checkLogin";
	form1.submit();
}

document.onkeydown = function(event) {
	event = event ? event : window.event;
	var keyCode = event.which ? event.which : event.keyCode;
	if($.browser.msie){ 
		if (keyCode == 13 && event.srcElement.type != 'button'
			&& event.srcElement.type != 'submit'
				&& event.srcElement.type != 'reset'
				&& event.srcElement.type != 'textarea'
				&& event.srcElement.type != '') {
			checkLogin();
		}
	}else if($.browser.safari){ 
		if (keyCode == 13 && event.srcElement.type != 'button'
			&& event.srcElement.type != 'submit'
				&& event.srcElement.type != 'reset'
				&& event.srcElement.type != 'textarea'
				&& event.srcElement.type != '') {
			checkLogin();
		}
	}else if($.browser.mozilla){
		if (keyCode == 13 && event.srcElement==undefined) {
			checkLogin();
		}
	}else if($.browser.opera) { 
		
	}			
}

 $().ready(function (){
	var iserror = '${loginerror}';				
	if(iserror==1){
		$.messager.alert('提示', '用户名或密码错误,请重新输入!','info');
	}				
});
	
 
</script>
</head>

<body>
<form id="form1" method="post">
<div class="login">
  <input type="text" value=""  class="txt_int user_name" 
  		value="${username}" id="username" name="username" 
	  	onfocus="if(this.value=='请输入用户ID'){this.value='';}"
	  	onblur="if(this.value==''){this.value='请输入用户ID';}" onkeydown/>
	  	
  <input type="password" value=""  class="txt_int pass_word"
  		value="" id="pwd" name="pwd"
	  	onfocus="if(this.value=='111111'){this.value='';}"
		onblur="if(this.value==''){this.value='1';}" onkeydown/>
  <input type="button" value="&nbsp;" class="login_in" onclick="checkLogin()"  />
  
</div>
</form>
</body>
</html>