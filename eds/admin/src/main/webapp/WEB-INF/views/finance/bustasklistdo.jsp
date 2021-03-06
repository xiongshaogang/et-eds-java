
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>    
<%@page import="com.edaisong.core.util.PageHelper"%>     
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.ParseHelper"%> 
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.entity.domain.BusTaskList"%>
<%String basePath =PropertyUtils.getProperty("java.admin.url");%>
<%String StarStr=request.getAttribute("sDate").toString(); %>
<%String EndStr=request.getAttribute("eDate").toString();%>

<%String netUrl=PropertyUtils.getProperty("net.admin.url");
//TODO 以后java开放订单审核管理 这里的门店名称跳转就要按照登录来源区分跳转 茹化肖
	PagedResponse<BusTaskList> data = (PagedResponse<BusTaskList>) request.getAttribute("listData");
%>
<%if(data.getResultList()==null||data.getResultList().size()==0) { %>
=====暂无数据=====
<%} else{%>
		<table class="table table-striped table-bordered table-hover dataTables-example">
			<thead>
				<tr class="tdbg">
						<th width="%5">编号</th>
						<th width="%5">门店名称</th>
						<th width="%5">注册电话</th>
						<th width="%5">待审核任务量</th>
						<th width="%5">待审核订单量</th>
				</tr>
			</thead>
			
			<tbody>                           
		
				 <%
		       	  List<BusTaskList> list = data.getResultList();
		       		 
		         	for (int i = 0; i < list.size(); i++) {%>  
					 <tr>
						<td><%=list.get(i).getBusinessId() %></td>
						<td><a target='_blank' href='<%=netUrl%>/Order/OrderAudit?businessName=<%=list.get(i).getName()%>&businessPhone=<%=list.get(i).getPhoneNo()%>&startDate=<%=StarStr%>&endDate=<%=EndStr%>'><%=list.get(i).getName()%></a></td>
						<td><%=list.get(i).getPhoneNo() %></td>						
						<td><%=list.get(i).getOrderCount() %></td>
						<td><%=list.get(i).getTaskCount() %></td>
					</tr>
				  <%}%> 	 	
			</tbody>
		</table>
		  <%}%> 
		<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>