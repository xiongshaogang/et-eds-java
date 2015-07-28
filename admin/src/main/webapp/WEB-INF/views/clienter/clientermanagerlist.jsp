<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.List"%>    
	<%@page import="com.edaisong.core.common.PageHelper"%>     
<%@page import="com.edaisong.entity.domain.ClienterModel"%> 
<%@page import="com.edaisong.entity.resp.ClienterResp"%>
<%@page import="java.util.ArrayList"%>
<%
	String basePath = request.getContextPath();
%>


    <link href="<%=basePath%>/css/admin.css" rel="stylesheet" /> 
    <script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/admin.js""></script>
        
	
		<table id="content">
			<thead>
				<tr class="tdbg">
						<th width="%5">序号</th>
						<th width="%5">姓名</th>
						<th width="%5">工作状态</th>
						<th width="%5">电话</th>
						<th width="%5">身份证号</th>
						<th width="%5">照片</th>
						<th width="%5">申请时间</th>
						<th width="%5">帐户余额</th>
						<th width="%5">可提现余额</th>
						<th width="%5">集团名称</th>
						<th width="%5">物流公司名称</th>
						<th width="%5">审核状态</th>
						<th width="%5">推荐人</th>
						<th width="%5">操作</th>	
				</tr>
			</thead>
			
			<tbody>                           

		<%		
		ClienterResp data = (ClienterResp) request.getAttribute("listData");
		
		List<ClienterModel> list = data.getClienterList();
		if (list == null) {
			list = new ArrayList<ClienterModel>();
		}		
		 for (int i = 0; i < list.size(); i++) {
			 %>  
			 <tr>
				<td><%=list.get(i).getId() %></td>
				<td><%=list.get(i).getTrueName() %></td>
				<%
				if (list.get(i).getWorkStatus()== 0)
				{
				%>		
				<td>上班</td>
				<%
				}
				else
				{
				%>
				<td>下班</td>
				<%
				}
				%>		
				<td><%=list.get(i).getPhoneNo() %></td>
				<td><%=list.get(i).getIdCard() %></td>
				<td><a href="javascript:void(0)" class="businessOk" onclick="">查看</a></td>			
				<td><%=list.get(i).getInsertTime() %></td>				
				<td style="color:red;font-weight:600"><a href="/SuperManManager/ClienterDetail?clienterId=@item.Id ">￥  <%=list.get(i).getAccountBalance() %></a></td>
				<td><%=list.get(i).getAllowWithdrawPrice() %></td>
				<td><%=list.get(i).getGroupName()%>  </td>
				<td><%=list.get(i).getDeliveryCompanyName()%>  </td>			
				<%
				if (list.get(i).getStatus()== 0)
				{
				%>		
				<td>审核被拒绝</td>
				<%
				}
				else if (list.get(i).getStatus()== 1)
				{
				%>
				<td>审核通过</td>
				<%
				}
				else if (list.get(i).getStatus()== 2)
				{
				%>
				<td>未审核</td>
				<%
				}
				else if (list.get(i).getStatus()== 3)
				{
				%>
				<td>审核中</td>
				<%
				}
				%>	
				 <td><%=list.get(i).getRecommendName()%>  </td>
				 			
				
				<%
				if (list.get(i).getStatus()== 1)
				{
				%>				
				<td>
				<a href="javascript:void(0)" style="color:gray"  onclick="clientOk('<%=list.get(i).getId() %>','<%=list.get(i).getIdCard() %>','<%=list.get(i).getTrueName() %>','<%=list.get(i).getPicUrl() %>','<%=list.get(i).getPicWithHandUrl() %>')">审核通过</a>
				<a href="javascript:void(0)"  onclick="clientCancel('<%=list.get(i).getId() %>')" >审核拒绝</a>
				</td>	
				<%
				}
				else
				{
				%>
				<td>				  
				  <a href="javascript:void(0)"   onclick="clientOk('<%=list.get(i).getId() %>','<%=list.get(i).getIdCard() %>','<%=list.get(i).getTrueName() %>','<%=list.get(i).getPicUrl() %>','<%=list.get(i).getPicWithHandUrl() %>')">审核通过</a>
                     <a href="javascript:void(0)" style="color:gray" onclick="clientCancel('<%=list.get(i).getId() %>')" >审核拒绝</a>
				</td>
				<%
				}
				%>	
				<td>
				<a href="javascript:void(0)" onclick="funcClienterRecharge('<%=list.get(i).getId() %>','<%=list.get(i).getTrueName() %>', '<%=list.get(i).getPhoneNo() %>')">余额变更</a>
				</td>
				
			</tr>
		 <%}
		%> 	 	
			</tbody>
		</table>
		<%=PageHelper.GetPage(data.getPageSize(),
					data.getCurrentPage(), data.getTotalRecord(),
					data.getTotalPage())%>


	
<script type="text/javascript">
   function clientOk(clientId, idCard, trueName, picUrl, picWithHandUrl) {
        if (!window.confirm("是否审核通过？")) {
            return;
        }
        if (idCard == 0 || trueName == 0 || picUrl == 0 || picWithHandUrl == 0) {
            alert("该骑士未上传个人资料，不能通过审核。")
            return;
        }
        var paramaters = { "id": clientId };        
        var url = "<%=basePath%>/clienter/auditok";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {            
                    window.location.href = "<%=basePath%>/clienter/list";               
            }
        });
    }
   function clientCancel(clientId) {
     
       var paramaters = { "id": clientId };        
       var url = "<%=basePath%>/clienter/auditcancel";
       $.ajax({
           type: 'POST',
           url: url,
           data: paramaters,
           success: function (result) {            
                   window.location.href = "<%=basePath%>/clienter/list";               
           }
       });
   }

   //骑士余额变更
   function funcClienterRecharge(id, name, phone) {
       $('#clienterId').val(0);
       $('#clienterName').val('');
       $('#clienterPhone').val('');
       $('#clienterRechargeAmount').val('');
       $('#rechargeLog').val('');
       $('#clienterId').val(id);
       $('#clienterName').val(name);
       $('#clienterPhone').val(phone);
       adminjs.openwinbox('#ClienterRechargeShow');
   }
  </script>
	
	