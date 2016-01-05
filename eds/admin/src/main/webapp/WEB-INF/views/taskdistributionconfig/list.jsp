
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>         
<%@page import="com.edaisong.entity.Group"%> 
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.DeliveryCompany"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.core.util.HtmlHelper"%>

<%
	String basePath =PropertyUtils.getProperty("java.admin.url");	 
%>
<link rel="stylesheet" href="<%=basePath%>/css/plugins/datapicker/datepicker3.css" />
<script src="<%=basePath%>/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<style type="text/css">
#map_contain {
    height: 90%;
    max-width: none;
}
label {
    max-width: none;
}

#control {
width: 100%;
}
</style>
<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">			
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">距离:</label>
							<div class="col-sm-8">						
								<input type="text" class="form-control" value="" name="txtKM" id="txtKM" />
							</div>
						</div>
					</div>					
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label">重量:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" value="" name="txtKG" id="txtKG" />
							</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">
								
							</div>
						</div>
					</div>
				</div>				
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">							
   						</div>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"></label>
							<div class="col-sm-8">
							  
							</div>
						</div>
					</div>
				</div>

			    <div class="row">
						<div class="col-lg-3">
						<button type="button" class="btn btn-w-m btn-primary" id=btnSearch
							style="margin-left: 3px;height:30px;">查询</button>
								<button type="button" class="btn btn-w-m btn-primary" id="add"  onclick="showAdd()"
					style="margin-left: 3px;height:30px;">新增		</button>	
					 
					</div>
			</div>
			<Br/>
			
			    <div class="row">
						<div class="col-lg-3">						
						<button type="button" class="btn btn-w-m btn-primary" id="btnCalculator"  onclick="showCalculator()"
					style="margin-left: 3px;height:30px;">配送费计算器
					</button>
					 
					</div>
			</div>
		</div>
	</div>	
</div>
<div id="content"></div>

<div tabindex="-1" class="modal inmodal" id="addConfig"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">添加配送费</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
            <div class="control-group">  
                <label>距离：</label><input  name="txtUKM" id="txtUKM" type="text" >
            </div>
            <div class="control-group">  
                <label>重量：</label><input  name="txtUKG" id="txtUKG" type="text" >
            </div>
            <div class="control-group">  
                <label>计价阶梯：</label><input  name="txtUSteps" id="txtUSteps" type="text" >
            </div>            
              <div class="control-group">  
                <label>金额：</label><input  name="txtUDistributionPrice" id="txtUDistributionPrice" type="text" >
            </div>
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnAdd" onclick="saveAdd()">保存</button>
				</div>
			</small>
		</div> 
	</div> 
</div>
   
 <div tabindex="-1" class="modal inmodal" id="modifyConfig"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">修改配送费</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
            <div class="control-group">  
                <label>距离：</label><input  name="txtEKM" id="txtEKM" type="text" >
                <input  name="txtEId" id="txtEId" type="hidden">
                <input  name="txtEIsMaster" id="txtEIsMaster" type="hidden">
            </div>
            <div class="control-group">  
                <label>重量：</label><input  name="txtEKG" id="txtEKG" type="text" >
            </div>
                    <div class="control-group">  
                <label>计价阶梯：</label><input  name="txtESteps" id="txtESteps" type="text" >
            </div>     
              <div class="control-group">  
                <label>金额：</label><input  name="txtEDistributionPrice" id="txtEDistributionPrice" type="text" >
            </div>
              <div class="control-group">  
                <label>备注：</label>
                <textarea name="txtERemark" id="txtERemark" style="width:200px;height:80px;"></textarea>               
            </div>
            
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnAdd" onclick="saveModify()">保存</button>
				</div>
			</small>
		</div> 
	</div> 
</div>

<div tabindex="-1" class="modal inmodal" id="CalculatorConfig"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">配送费计算器</h4>
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
            <br>
            <div class="control-group">  
                <label>距离：</label><input  name="txtCKM" id="txtCKM" type="text" >
            </div>
            <div class="control-group">  
                <label>重量：</label><input  name="txtCKG" id="txtCKG" type="text" >
            </div>      
              <div class="control-group">  
                <label>配送费：</label><input  name="txtPSF" id="txtPSF" type="text"  disabled="disabled">
            </div>       
        </fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button" id="btnCCalculator" onclick="CalculatorFun()">计算公里</button>
				</div>
			</small>
		</div> 
	</div> 
</div>
	<script>		
	var jss={
			search:function(currentPage){	
			   var KM = $("#txtKM").val();
				   var KG=$("#txtKG").val();	 		
				 var paramaters = { 
						 "currentPage":currentPage,						
						 "KM": KM,
						 "KG": KG,					
						 };        
			        var url = "<%=basePath%>/taskdistributionconfig/listdo";
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

	function showAdd(){ 
        $('#txtUKM').val('0');
        $('#txtUKG').val('0');
        $('#txtUSteps').val('1');
        $('#txtUDistributionPrice').val('0');
        $('#addConfig').modal('show');
}
	function saveAdd(){
		var txtKM= $('#txtUKM').val().trim();
		var txtKG= $('#txtUKG').val().trim();	
		var txtSteps= $('#txtUSteps').val().trim();
		var txtDistributionPrice= $('#txtUDistributionPrice').val().trim();		

		 if(txtKM == "")
		 {
			 alert("距离不能为空");
		    return;
		 }
		 if(txtKG == "")
		 {
			 alert("重量不能为空");
		    return;
		 }
		 if(txtKM>0 && txtKG>0)
    	 {
			 alert("距离， 重量只能配置1个值");
			return;		 
		 }
		 if(txtKM<=0 && txtKG<=0)
    	 {
			 alert("距离， 重量必须有1个值不能为0");
			return;		 
		 }
		 if(txtUSteps == "")
		 {
			 alert("计价阶梯不能为空");
		    return;
		 }
		 if(txtUSteps == "0")
		 {
			 alert("计价阶梯不能为0");
		    return;
		 }
		 
		 if(txtDistributionPrice == "")
		 {
			 alert("配送费");
		    return;
		 }
		if(txtDistributionPrice<=0){
		    	alert("配送费必须大于零");
		    	return;
		   }		    
	    
	    var paramaters = {
                "KM": txtKM.trim(),
                "KG": txtKG.trim(),
                "Steps": txtSteps.trim(),
                "DistributionPrice": txtDistributionPrice.trim(),
            };
	    
       var url = "<%=basePath%>/taskdistributionconfig/add";
       var la= layer.confirm('是否确认创建配置费？', {
		    btn: ['确认','取消'], //按钮
		    shade: false //显示遮罩
		},function(){
			layer.close(la);
			$.ajax({
		           type: 'POST',
		           url: url,
		           data: paramaters,
		           success: function (result) {		    
		        	   alert(result.message);		        	
		               if (result.status == 1) {
		            	   window.location.href = "<%=basePath%>/taskdistributionconfig/list";		
		               }
		                              
		        	  
		           }
		       });
		});  
	}
	
	function saveModify()
	{
		var txtId= $('#txtEId').val().trim();
		var txtKM= $('#txtEKM').val().trim();
		var txtKG= $('#txtEKG').val().trim();
		var txtESteps= $('#txtESteps').val().trim();
		var txtERemark= $('#txtERemark').val().trim();	
		var txtDistributionPrice= $('#txtEDistributionPrice').val().trim();		
		var txtEIsMaster= $('#txtEIsMaster').val().trim();		

		 if(txtKM == "")
		 {
			 alert("距离不能为空");
		    return;
		 }
		 if(txtKG == "")
		 {
			 alert("重量不能为空");
		    return;
		 }
		 if (txtEIsMaster==0)
			 {
				 if(txtKM>0 && txtKG>0)
		    	 {
					 alert("距离， 重量只能配置1个值");
					return;		 
				 }
				 if(txtKM<=0 && txtKG<=0)
		    	 {
					 alert("距离， 重量必须有1个值不能为0");
					return;		 
				 }
				 if(txtDistributionPrice == "")
				 {
					 alert("配送费");
				    return;
				 }
				if(txtDistributionPrice<=0){
				    	alert("配送费必须大于零");
				    	return;
				   }	
				 if(txtESteps == "")
				 {
					 alert("计价阶梯不能为空");
				    return;
				 }
				 if(txtESteps == "0")
				 {
					 alert("计价阶梯不能为0");
				    return;
				 }
			 }
	    var paramaters = {	    		
	    		"Id": txtId.trim(),
                "KM": txtKM.trim(),
                "KG": txtKG.trim(),
                "Steps": txtESteps.trim(),                
                "DistributionPrice": txtDistributionPrice.trim(),
                "IsMaster": txtEIsMaster.trim(),
                "Remark": txtERemark.trim(),
            };
       var url = "<%=basePath%>/taskdistributionconfig/modify";
	   var la= layer.confirm('是否确认修改配置费？', {
		    btn: ['确认','取消'], //按钮
		    shade: false //显示遮罩
		},function(){
			layer.close(la);
			$.ajax({
		           type: 'POST',
		           url: url,
		           data: paramaters,
		           success: function (result) {		    
		        	   alert(result.message);		        	
		               if (result.status == 1) {
		            	   window.location.href = "<%=basePath%>/taskdistributionconfig/list";		
		               }
		                              
		        	  
		           }
		       });
		});       	    
	}
	function showCalculator(){ 
		 $('#txtCKM').val('0');
	     $('#txtCKG').val('0');
	     $('#txtPSF').val('0');
        $('#CalculatorConfig').modal('show');
}
	
	function CalculatorFun(){	        
	        
		var txtKM= $('#txtCKM').val().trim();
		var txtKG= $('#txtCKG').val().trim();	
		

		 if(txtKM == "")
		 {
			 alert("距离不能为空");
		    return;
		 }
		 if(txtKG == "")
		 {
			 alert("重量不能为空");
		    return;
		 }	
	    
	    var paramaters = {
                "KM": txtKM.trim(),
                "KG": txtKG.trim()           
            };
        var url = "<%=basePath%>/taskdistributionconfig/calculator"; 
	
			$.ajax({
		           type: 'POST',
		           url: url,
		           data: paramaters,
		           success: function (result) {		   
		        	   
		        	   $('#txtPSF').val(result);

		           }
		       });	       	    
	}
	</script>		
	
