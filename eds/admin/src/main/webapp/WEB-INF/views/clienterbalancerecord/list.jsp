<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
String basePath =PropertyUtils.getProperty("java.admin.url");
    String  clienterId=	request.getAttribute("clienterId").toString();
%>

<div>
骑士Id:<%=clienterId%>   
</div>



        
 <div id="content">
	
 </div>


	<script>		
	var jss={
			search:function(currentPage){	

                 var clienterId = <%=clienterId%>;            
                 //参数不能为""值
				 var paramaters = { 
						 "currentPage":currentPage,
						 "clienterId": clienterId						
						 };        
			        var url = "<%=basePath%>/clienter/clienterbalancerecordlistdo";
			        $.ajax({
			            type: 'POST',
			            url: url,
			            data: paramaters,
			            success: function (result) {         
			            	$("#content").html(result);               
			            }
			        });
			}
		}
	

		
	jss.search(1);
	$("#btnSearch").click(function(){
		jss.search(1);
	});
	

	</script>		
	
