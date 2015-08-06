<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="java.sql.Date"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.core.common.ParseHelper"%>

<%	
String basePath =PropertyUtils.getProperty("static.admin.url");
%>
<script src="<%=basePath%>/js/jquery-2.1.1.js"></script>
<form>
日期:<input type="text" name="txtstartDate" id="startDate">到<input type="text" name="txtendDate" id="endDate"><br/>
交易类型:<select id="transTypeSelect">
<option value="0">全部</option>
<option value="1">发布订单</option>
<option value="2">取消订单</option>
<option value="8">订单菜品费</option>
<option value="9">充值</option>
<option value="11">手续费</option>
</select>
<select id="numTypeSelect">
<option value="0">单号类型</option>
<option value="1">订单号</option>
<option value="2">流水号</option>
</select>
<input type="text" name="txtnumString" id="numString" /> 
<button type="button" id="btnSerach" >查询</button>
</form>
<div id="dataList">
</div>
<script>
$(function(){
	//设置文本框不可用
	$('#numString').attr('disabled','disabled');
	//单号类型改变事件
	$('#numTypeSelect').change(function(){
		var numtype=$('#numTypeSelect option:selected').val();
		if(numtype==0){
			$('#numString').attr('disabled','disabled');
		}
		else{
			$('#numString').removeAttr('disabled');
		}
	});
	$('#btnSerach').click(function(){
		PostData(1);
	});
	PostData(1);
});
var jss = {
		search : function(currentPage) {
		$("#_hiddenCurrentPage").val(currentPage);
		 var data=$("#searchForm").serialize();
			$.post('<%=basePath%>/transdetail/listdo',data, function(d) {
				$("#content").html(d);
			});
		}
	}
//获取页面数据
function PostData(currentPage){
	var url='http://localhost:8080/business/transdetail/listdo'
	var paramaters={
			startDate:$('#startDate').val(),
			endDate:$('#endDate').val(),
			transType:$('#transTypeSelect option:selected').val(),
			numType:$('#numTypeSelect option:selected').val(),
			numString:$('#numString').val(),
			businessid:1791}
	$.ajax({
        type: 'POST',
        url: url,
        data: paramaters,
        success: function (data) {
        	alert(data);
        	$('#dataList').html(data);
        }
    });
};

</script>