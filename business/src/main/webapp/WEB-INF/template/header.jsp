<%@page import="com.edaisong.entity.Business"%>
<%@page import="com.edaisong.business.common.UserContext"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.business.url");
    UserContext userContext = UserContext.getCurrentContext(request);
	String name = userContext==null ? "游客" : userContext.getBusinessName();
%>
<div class="header">
	<a class="logo fl" href="<%=basePath%>/index"><img src="<%=basePath %>/images/logo.png" width="74" height="25" alt=""></a>
	<b class="fl">商家中心</b>
	<p class="fr">
		<a class="fr" href="<%=basePath %>/account/logoff">退出</a>
		<em class="fr">|</em>
		<span class="fr">
			您好，
			<%-- <a href="javascript:;"><%=name%></a> --%>
			<%=name%>
		</span>
	</p>
</div>