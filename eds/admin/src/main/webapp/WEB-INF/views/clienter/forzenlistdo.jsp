
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.util.PageHelper"%>
<%@page import="com.edaisong.entity.ClienterForzen"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.edaisong.entity.common.PagedResponse"%>
<%@page import="com.edaisong.core.util.ParseHelper"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.admin.url");
%>
<%
	PagedResponse<ClienterForzen> data = (PagedResponse<ClienterForzen>) request.getAttribute("listData");
%>
<% if(data.getResultList()==null||data.getResultList().size()==0) 
{%>
	=====暂无数据=====
<%} else{%>
		<table class="table table-striped table-bordered table-hover dataTables-example">
			<thead>
				<tr class="tdbg">
						<th width="%5">冻结单号</th>
						<th width="%5">骑士姓名</th>
						<th width="%5">骑士电话</th>
						<th width="%5">冻结金额</th>
						<th width="%5">冻结日期</th>
						<th width="%5">解冻日期</th>
						<th width="%5">冻结状态</th>
						<th width="%5">操作人</th>
						<th width="%5">操作</th>	
				</tr>
			</thead>
			<tbody>                           
		<%List<ClienterForzen> list = data.getResultList();
           for (int i = 0; i < list.size(); i++) {%>  
			 <tr> 
				<td><a href="javascript:void(0)" onclick="showForzenDetail(<%=list.get(i).getId()%>,'<%=list.get(i).getClienterName() %>','<%=list.get(i).getClienterPhone()%>',<%=list.get(i).getForzenamount() %>,'<%=list.get(i).getForzenreason() %>','<%=list.get(i).getThawreason() %>')"><%=list.get(i).getId()%></a></td>
				<td><%=list.get(i).getClienterName() %></td>

				<td><%=list.get(i).getClienterPhone() %></td>

				<td><%=list.get(i).getForzenamountString() %></td>

				<td><%=ParseHelper.ToDateString(list.get(i).getForzendate())%></td>

				<td><%=ParseHelper.ToDateString(list.get(i).getThawdate())%></td>

				<td><%=list.get(i).getStatus()==1?"冻结中":"已解冻" %></td>

				<td><%=list.get(i).getOperator()%></td>
				<td><%if(list.get(i).getStatus()==1){%>
								<a href="javascript:showUnfreeze(<%=list.get(i).getId()%>,<%=list.get(i).getClienterid()%>,<%=list.get(i).getForzenamount() %>)">解冻</a>
								<%}else{%> <%}%>
				</td>				
			</tr>
		 <%}%> 	 	
			</tbody>
		</table>
<% }%>

<%=PageHelper.getPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>
<script>
	function showUnfreeze(id,clienterId,forzenAmount){
		$("#hdForzenId").val(id); 
		$("#hdClienterId").val(clienterId);
		$("#hdForzenAmount").val(forzenAmount);
		$('#showUnfreezeClienter').modal('show');	
	}

//显示冻结单详情
function showForzenDetail(forzenId,clienterName,clienterPhone,forzenAmount,forzenReason,unfreezeReason){
	$("#hdForzenId").val(forzenId); 
	$("#lblclienterTrueName").html(clienterName);
	$("#lblclienterPhone").html(clienterPhone);
	$("#lblforzenAmount").html(forzenAmount);
	$("#lblforzenReason").html(forzenReason);
	$("#lblunfreezeReason").html(unfreezeReason);
	var url = "<%=basePath%>/clienter/getforzenlog";
    $.ajax({
        type: 'POST',
        url: url,
        data: {"forzenId":$("#hdForzenId").val()},
        success: function (result) {
     	   $("#forzenRecord").html(result.message);        	  
        }
    }); 
    
	$('#showForzenOrderDetail').modal('show');	
}
</script>
