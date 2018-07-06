<%@ page language="java"  pageEncoding="UTF-8"%>  <!-- errorPage="/error.jsp" -->

<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
pageContext.setAttribute("basePath", basePath); 
%>
<%-- JSTL 标签 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="mf" uri="/MyFramework-tags"%>
<%-- <%@ taglib prefix="mf" uri="/MyFramework-tags"%> --%>
<%-- 绝对路径 --%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" href="${ctx}/plugin/layui/css/layui.css" media="all" />
<script type="text/javascript" src="${ctx}/plugin/layui/layui.js"></script>	
<script type="text/javascript">
var CTX="${ctx}";
var basePath="<%=basePath%>";
</script>