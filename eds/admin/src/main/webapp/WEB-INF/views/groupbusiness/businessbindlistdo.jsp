<%@page import="com.edaisong.admin.common.UserContext"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.util.Date"%>
<%@page import="com.edaisong.core.util.StringUtils"%>
<%@page import="com.edaisong.entity.domain.GlobalConfigModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.entity.domain.GroupBusinessRelationModel"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
PagedResponse<GroupBusinessRelationModel> responsePageList=	(PagedResponse<GroupBusinessRelationModel>)request.getAttribute("listData");
List<GroupBusinessRelationModel> data = responsePageList.getResultList();
String basePath =PropertyUtils.getProperty("java.admin.url");
if(data == null){
	data = new ArrayList<GroupBusinessRelationModel>();
	
}
UserContext context=UserContext.getCurrentContext(request);
int loginFrom=context.getLoginFrom();
String alink="";
String alink2="";
if (loginFrom==0) {
	alink=PropertyUtils.getProperty("net.admin.url") + "/BusinessManager/BusinessManager";
	alink2=PropertyUtils.getProperty("net.admin.url") + "/BusinessManager/BusinessDetail";
}else {
	alink=PropertyUtils.getProperty("java.admin.url")+"/business/list";
	alink2=PropertyUtils.getProperty("java.admin.url")+"/business/balancedetail";
}

%>
<table
						class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th>编号</th>
								<th>门店名称</th>
								<th>电话</th>
								<th>门店余额</th>
								<th>消费集团金额</th>
								<th>绑定日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<%for (int i = 0; i < data.size(); i++) { %>
							<tr class="info">
								<td><%=data.get(i).getId() %></td>
								<td><a href="<%=alink%>?businessName=<%=data.get(i).getName()%>&businessPhone=<%=data.get(i).getPhoneNo()%>"><%=ParseHelper.ShowString(data.get(i).getName())%></a></td>
								<td><%=data.get(i).getPhoneNo()%></td>
								<td><a href="<%=alink2%>?businessId=<%=data.get(i).getBusinessid()%>"><%=data.get(i).getBalancePriceString()%></a></td>
								<td><%=data.get(i).getUseGroupMoneyString()%></td>
								<td><%=ParseHelper.ToDateString(data.get(i).getUpdatetime())%></td>
		                        <td>
		                        	<%if(data.get(i).getIsbind() == 1){%>
		                        		<button type="button" class="btn btn-primary btn-sm" onclick="funRemoveBusinessBind(<%=data.get(i).getBusinessid()%>)">移除绑定</button>
		                        	<% }%>
		                        </td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
<%=PageHelper.getPage(responsePageList.getPageSize(),
		responsePageList.getCurrentPage(), responsePageList.getTotalRecord(),
		responsePageList.getTotalPage())%>