<%@ page language="java"  pageEncoding="UTF-8"%>  <!-- errorPage="/error.jsp" -->

<%-- JSTL 标签 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%-- <%@ taglib prefix="mf" uri="/MyFramework-tags"%> --%>
<%-- 绝对路径 --%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="layui-admin/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="layui-admin/css/font_eolqem241z66flxr.css" media="all" />
<link rel="stylesheet" href="layui-admin/css/main.css" media="all" />
<script type="text/javascript" src="layui-admin/layui/layui.js"></script>
<script type="text/javascript">
var CTX="${ctx}";
</script>