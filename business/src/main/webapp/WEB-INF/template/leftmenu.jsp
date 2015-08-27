
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("static.business.url");
    String viewPath =request.getAttribute("viewPath").toString();
%>
	<div class="nav">
		<img src="<%=basePath%>/images/dun.png" width="55" height="74" alt="">
		<a href="<%=basePath%>/order/publish">发布任务</a>
		<span <%=viewPath=="index"?"class='on'":""%>><a class="one" href="<%=basePath%>/index">商户主页</a></span>
		<span <%=viewPath=="order/list"?"class='on'":""%>><a class="two" href="<%=basePath%>/order/list">全部订单</a></span>
<!-- 	<span <%=viewPath==""?"class='on'":""%>><a class="three" href="javascript:;">订单统计</a></span> -->
		<span <%=viewPath=="transdetail/list"?"class='on'":""%>><a class="four" href="<%=basePath%>/transdetail/list">交易明细</a></span>
		<span <%=viewPath=="clienter/list"?"class='on'":""%>><a class="five" href="<%=basePath%>/clienter/list">骑士管理</a></span>
		<span <%=viewPath=="message/list"?"class='on'":""%>><a class="six" href="<%=basePath%>/message/list">消息中心</a></span>
	</div>