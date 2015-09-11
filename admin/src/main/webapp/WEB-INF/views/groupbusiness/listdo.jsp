<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.GroupBusinessModel"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>

<%
	String basePath = PropertyUtils.getProperty("static.admin.url");
%>
<table
	class="table table-striped table-bordered table-hover dataTables-example">
	<thead>
		<tr class="tdbg">
			<th width="%5">编号</th>
			<th width="%5">集团名称</th>
			<th width="%5">余额</th>
			<th width="%5">手机号</th>
			<th width="%5">创建时间</th>
			<th width="%5">操作人</th>
			<th width="%5">操作</th>
		</tr>
	</thead>
	<tbody>

		<%
			PagedResponse<GroupBusinessModel> data = (PagedResponse<GroupBusinessModel>) request.getAttribute("listData");
			List<GroupBusinessModel> list = data.getResultList();
			if (list == null) {
				list = new ArrayList<GroupBusinessModel>();
			}
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getGroupbusiname()%></td> 
			<td><%=list.get(i).getAmount()%></td>
			<td><%=list.get(i).getLoginname()%></td>
			<td><%=ParseHelper.ToDateString(list.get(i).getCreatetime())%></td>
			<td><%=list.get(i).getCreatename()%></td>
			<td><a href="javascript:void(0)" class="businessOk" onclick="showMidfyGroupBusiness(<%=list.get(i).getId()%>,'<%=list.get(i).getGroupbusiname()%>','<%=list.get(i).getLoginname()%>')">修改</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>
<script>
function showMidfyGroupBusiness(id,groupbusiName,loginName){
	$("#modifyGroupBusinessLog").html(""); 
	 $('#txtModifyGroupBusinessName').val(groupbusiName);
     $('#txtModifyLoginName').val(loginName);
     $("#txtModifyGroupBusinessId").val(id);
     var url = "<%=basePath%>/groupbusiness/getgroupbusinesslog";
     $.ajax({
         type: 'POST',
         url: url,
         data: {"id":id},
         success: function (result) {
      	   $("#modifyGroupBusinessLog").html(result.message);        	  
         }
     });
     
     $('#modifyGroupBusiness').modal('show');
}

</script>