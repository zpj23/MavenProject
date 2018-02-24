<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL 标签 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%-- <%@ taglib prefix="mf" uri="/MyFramework-tags"%> --%>
<%-- 绝对路径 --%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx}js/jquery/jquery-1.10.1.min.js"></script>

<script type="text/javascript">

function subData() {
    $("#myform").submit();
}
</script>
</head>
<body>
这是跳转成功的表单
<form id="myform" action="${ctx}/login/testPage" method="post">
        <table cellpadding="0" cellspacing="0" border="0" class="tab_case_01"  >
             <tr>
                <td colspan="1" style="text-align: right;">
                  <input type="button" id="seache" value="测试跳转" onclick="subData();" class="button icon edit" />
                </td>
             </tr>
            </table>
        </div>
    </form>
</body>
</html>