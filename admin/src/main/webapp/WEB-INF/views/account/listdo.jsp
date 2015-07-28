<%@page import="com.edaisong.entity.common.ResponsePageList"%>
<%@page import="com.edaisong.entity.resp.AccountResp"%>
<%@page import="com.edaisong.core.common.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.Account"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr>
			<th width="5%">编号</th>
			<th>账号名称</th>
			<th>登录名称</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			ResponsePageList<Account> data = (ResponsePageList<Account>) request
					.getAttribute("listData");
			List<Account> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<Account>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getUsername()%></td>
			<td><%=list.get(i).getLoginname()%></td>
			<td><%=list.get(i).getStatus() == 1 ? "√" : "×"%></td>
			<td><a data-toggle="modal" data-target="#myModal"
				href="javascript:void(0)">编辑</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.GetPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>