<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.edaisong.entity.domain.AreaModel"%>
<%@page import="com.edaisong.entity.BusinessGroup"%>
<%@page import="com.edaisong.entity.domain.GroupModel"%>
<%
	String basePath = request.getContextPath();
%>
<link href="<%=basePath%>/css/admin.css" rel="stylesheet" />

<div class="SearchMd">
	<%
		List<AreaModel> areaListData=	(List<AreaModel>)request.getAttribute("areaListData");
		List<BusinessGroup> businessGroupListData=	(List<BusinessGroup>)request.getAttribute("businessGroupListData");
		List<GroupModel> groupListData=	(List<GroupModel>)request.getAttribute("groupListData");
		int groupId=(int)request.getAttribute("groupId");
	%>
	<table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><span class="">商户名称: </span> <input id="txtBusinessName"
				name="BusinessName" type="tel" /> <span class="">审核状态: </span> <select
				name="status" class="selectw" id="businessStatus"
				style="width: 143px">
					<option value="-1" selected="selected">全部</option>
					<option value="1">审核通过</option>
					<option value="0">未审核</option>
					<option value="2">未审核且未添加地址</option>
					<option value="3">审核中</option>
					<option value="4">审核被拒绝</option>
			</select> <span class="">商户电话: </span> <input id="txtBusinessPhone"
				type="text" name="BusinessPhone" /> <span class="">结算比例: </span> <input
				id="txtBusinessCommission" type="text" name="BusinessCommission" />

			</td>
		</tr>
		<tr>
			<td><span class="">筛选城市: </span><select name="businessCity"
				id="businessCity" style="width: 155px">
					<option value="" selected="selected">--无--</option>
					<%
						for (int i = 0; i < areaListData.size(); i++) {
					%>
					<option value="<%=areaListData.get(i).getCode()%>"><%=areaListData.get(i).getName()%></option>
					<%
						}
					%>
			</select> <input id="txtGroupId" type="hidden" value="<%=groupId%>"
				name="GroupId" /> <span class="">商家分组:</span> <select
				name="BusinessGroupId" id="BusinessGroupId" class="selectw"
				style="width: 143px">
					<option value="" selected="selected">全部</option>
					<%
						for (int i = 0; i < businessGroupListData.size(); i++) {
					%>
					<option value="<%=businessGroupListData.get(i).getId()%>"><%=businessGroupListData.get(i).getName()%></option>
					<%
						}
					%>
			</select> <span class="">结算类型: </span> <select name="commissionType"
				class="selectw" id="CommissionMold" style="width: 143px">
					<option value="-1" selected="selected">全部</option>
					<option value="1">固定比例</option>
					<option value="2">固定金额</option>
			</select> <span class="">餐费结算方式: </span> <select name="MealsSettleMode"
				class="selectw" id="MealsSettleMode" style="width: 143px">
					<option value="-1" selected="selected">-请选择-</option>
					<option value="0">线下结算</option>
					<option value="1">线上结算</option>
			</select></td>
		</tr>
		<tr>
			<td><span class="">推荐人电话: </span> <input id="txtRecommendPhone"
				type="text" name="RecommendPhone" /> <input type="submit"
				value="查询" class="searchBtn" id="btnSearch" /> @if
				(SuperMan.App_Start.UserContext.Current.HasAuthority(40)) { <input
				type="submit" value="添加商户" data-toggle="modal"
				data-target="#BusinessAddDiv" class="searchBtn" id="btnAddShop" />
				}</td>
		</tr>
	</table>
</div>

<div id="groupList">
	<jsp:include page="list.jsp" />
</div>

<div class="add-openbox add-form" id="BusinessCommissionDiv"
	style="width: 500px">
	<h2 style="border: none">
		<p id="statusFin">商家结算-补贴策略</p>
	</h2>
	<fieldset>
		<input type="hidden" id="busCommissionHid" value="0" />
		<div class="control-group">
			<label>商家名称：</label> <input name="busCommissionName"
				id="busCommissionName" readonly="readonly" type="text">
		</div>
		<div class="control-group">
			<label>商家电话：</label> <input name="busCommissionPhone"
				id="busCommissionPhone" readonly="readonly" type="text">
		</div>
		<div class="control-group">
			<label>配 送 费：</label> <input name="busCommissionWaiSong"
				id="busCommissionWaiSong" style="width: 200px;" type="text">
			<input name="oldBusCommissionWaiSong" id="oldBusCommissionWaiSong"
				type="hidden">
		</div>
		<div class="control-group">
			<label style="font-size: 15px">结算比例设置(应收)</label>
		</div>
		<div class="control-group">
			<input id="rCommissionFormulaMode0" name="rCommissionFormulaMode"
				type="radio" value="1"> <label for="rCommissionFormulaMode0">结算比例</label>
			<input id="rCommissionFormulaMode1" name="rCommissionFormulaMode"
				type="radio" value="2" style="margin-left: 30px"> <label
				for="rCommissionFormulaMode1">固定金额</label> <input type="hidden"
				id="oldCommissionType" name="oldCommissionType">
		</div>
		<div class="control-group">
			<div id="divbusCommissionText">
				<input name="busCommissionText" id="busCommissionText"
					style="width: 120px;" type="text">% <input
					name="oldBusCommissionText" id="oldBusCommissionText" type="hidden">
			</div>
			<div id="divCommissionFixValue">
				<input name="CommissionFixValue" id="CommissionFixValue"
					style="width: 120px;" type="text">元/单 <input
					name="oldCommissionFixValue" id="oldCommissionFixValue"
					type="hidden">
			</div>
		</div>
	</fieldset>
	<fieldset style="border-top: none">
		<div class="control-group">
			<label style="font-size: 15px">补贴策略设置(应付)</label>
		</div>
		<div class="control-group" style="margin-left: 30px">
			<label id="labGlobalConfig"></label>
		</div>
		<div class="control-group" style="margin-left: 30px">
			<label>补贴策略：</label> <select name="businessGroupID"
				id="businessGroupID" class="selectw" style="width: 143px">
				<%
					for (int i = 0; i < businessGroupListData.size(); i++) {
				%>
				<option value="<%=businessGroupListData.get(i).getId()%>"><%=businessGroupListData.get(i).getName()%></option>
				<%
					}
				%>
			</select> <input name="oldStrategyID" id="oldStrategyID" type="hidden">
		</div>
	</fieldset>
	<p class="btnbox">
		<input value="确认" type="button" id="btnCommissionConfim"
			class="yesBtn" /> <input value="关闭" type="button"
			class="J_closebox qxBtn" />
	</p>
</div>

<div class="add-openbox add-form" id="BusiPicShow"
	style="width: 500px; height: 300px">
	<h2>
		<p id="statusFins">查看图片</p>
	</h2>
	<form class="form-horizontal" role="form" id="BusiPicForm"
		method="post">
		<fieldset>
			<img id="showBusiImage" /> <a id="showBigBusiImage" href=""
				target="_blank">查看大图</a>
		</fieldset>
		<p class="btnbox">
			<input value="关闭" type="button" class="J_closebox qxBtn" />
		</p>
	</form>
</div>

<div class="add-openbox add-form" id="BusinessInfoUpdateDiv"
	style="width: 500px">
	<h2>
		<p id="statusFin">修改商家信息</p>
	</h2>
	<fieldset>
		<input type="hidden" id="busiId" value="0" />
		<div class="control-group">
			<label>商家名称：</label> <input name="busiName" id="busiName" type="text">
		</div>
		<div class="control-group">
			<label>联系电话：</label> <input name="busiPhone" id="busiPhone"
				type="text">
		</div>
		<div class="control-group">
			<label>餐费结算方式：</label> <select name="busiMealsSettleMode"
				class="selectw" id="busiMealsSettleMode" style="width: 143px">
				<option value="0">线下结算</option>
				<option value="1">线上结算</option>
			</select>
		</div>
		<div class="control-group">
			<label>绑定第三方ID：</label> <input name="busiSourceId" id="busiSourceId"
				type="text"> <input type="hidden" name="oldBusiSourceId"
				id="oldBusiSourceId"> <input type="hidden"
				name="oldBusGroupId" id="oldBusGroupId"> <select
				name="busGroupId" id="busGroupId" class="selectw"
				style="width: 100px">
				<option value="">--请选择--</option>
				<%
					for (int i = 0; i < groupListData.size(); i++) {
				%>
				<option value="<%=groupListData.get(i).getId()%>"><%=groupListData.get(i).getGroupname()%></option>
				<%
					}
				%>
			</select>
		</div>
	</fieldset>
	<p class="btnbox">
		<input value="确认" type="button" id="btnUpdateBusinessInfo"
			class="yesBtn" /> <input value="关闭" type="button"
			class="J_closebox qxBtn" />
	</p>
</div>

<div tabindex="-1" class="modal inmodal" id="BusinessAddDiv"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">添加商户</h4>
				<!-- 				<small class="font-bold">这里可以显示副标题。 </small> -->
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
						<div class="control-group">
							<label>商家名称</label> <input name="businessName" id="businessName"
								type="text">
						</div>
						<div class="control-group">
							<label>电话</label> <input name="businessphoneNo"
								id="businessphoneNo" type="text">
						</div>
						<div class="control-group">
							<label>结算比例</label> <input name="businessCommission"
								id="businessCommission" type="text">%
						</div>
						<div class="control-group">
							<label>外送费</label> <input name="businessWaisong"
								id="businessWaisong" type="text">
						</div>
						<div class="control-group">
							<label>地址</label> <input name="businessaddr" id="businessaddr"
								style="width: 200px;" type="text">
						</div>
						<div class="control-group">
							<label>城市</label> <select name="businesscity" id="businesscity"
								style="width: 155px">
								<option value="" selected="selected">--无--</option>
								<%
									for (int i = 0; i < areaListData.size(); i++) {
								%>
								<option value="<%=areaListData.get(i).getCode()%>"><%=areaListData.get(i).getName()%></option>
								<%
									}
								%>
							</select>

						</div>
						<%
							if(groupId>0)
													{
						%>
						<input type="hidden" id="addbusinessGroupID" value="<%=groupId%>" />
						%><%
							}else {
						%>
						<div class="control-group">
							<label class="control-label" for="InternalDepart">集团信息</label> <select
								name="addbusinessGroupID" id="addbusinessGroupID"
								class="selectw" style="width: 143px">
								<option value="" selected="selected">--无--</option>
								<%
									for (int i = 0; i < groupListData.size(); i++) {
								%>
								<option value="<%=groupListData.get(i).getId()%>"><%=groupListData.get(i).getGroupname()%></option>
								<%
									}
								%>
							</select>
							<%
								}
							%>

						</div>


					</fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button">保存</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>
<div tabindex="-1" class="modal inmodal" id="BusinessRechargeShow"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">商户充值</h4>
				<!-- 				<small class="font-bold">这里可以显示副标题。 </small> -->
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
						<br>
						<div class="control-group">
							<label>商家名称：</label> <input name="busName" id="busName"
								disabled="disabled" type="text"> <input name="busId"
								id="busId" type="hidden">
						</div>
						<div class="control-group">
							<label>商家电话：</label> <input name="busPhone" id="busPhone"
								disabled="disabled" type="text">
						</div>
						<div class="control-group">
							<label>充值金额：</label> <input name="busRechargeAmount"
								id="busRechargeAmount" type="text">元 <label
								style="font-size: 10px; color: red">（充值金额范围:1.00-50000.00元）</label>
						</div>
						<div class="control-group">
							<label>操作描述：</label>
							<div class="controls">
								<textarea cols="45" rows="5" id="rechargeLog"></textarea>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button">保存</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>

<div tabindex="-1" class="modal inmodal" id="BusinessWithdraw"
	role="dialog" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" type="button" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title">发起商户提款申请</h4>
				<!-- 				<small class="font-bold">这里可以显示副标题。 </small> -->
			</div>
			<small class="font-bold">
				<div class="modal-body">
					<fieldset>
						<br>
						<div class="control-group">
							<label>商家名称：</label> <input name="withdrawName" id="withdrawName"
								disabled="disabled" type="text"> <input
								name="withdrawId" id="withdrawId" type="hidden">
						</div>
						<div class="control-group">
							<label>登录账号：</label> <input name="withdrawPhone"
								id="withdrawPhone" disabled="disabled" type="text">
						</div>
						<div class="control-group">
							<label>提款金额：</label> <input name="withdrawAmount"
								id="withdrawAmount" type="text">元 <label
								style="font-size: 10px; color: red">（提款金额范围:1-1,000,000元）</label>
						</div>
						<div class="control-group">
							<label>省：</label> <input type="text" disabled="disabled"
								id="openProvince" /> <input type="hidden" id="openProvinceCode" />
						</div>
						<div class="control-group">
							<label>市区：</label> <input type="text" disabled="disabled"
								id="openCity" /> <input type="hidden" id="openCityCode" /> 
						</div>
						<div class="control-group">
							<label>身份证号/营业执照：</label> <input type="text" id="idCard"
								disabled="disabled" />
						</div>
						<div class="control-group">
							<label>账户类型：</label> <input type="text" id="accountType"
								disabled="disabled" />
						</div>

						<div class="control-group">
							<label>备注：</label>
							<div class="controls">
								<textarea cols="45" rows="5" id="withdrawLog"></textarea>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="modal-footer">
					<button class="btn btn-white" type="button" data-dismiss="modal">关闭</button>
					<button class="btn btn-primary" type="button">保存</button>
				</div>
			</small>
		</div>
		<small class="font-bold"> </small>
	</div>
	<small class="font-bold"> </small>
</div>

<script>
    var adminjs = new adminglass(); //实例化后台类
    $(document).ready(function () {
        //GetOpenProvince(); 
        window.location.hash = '';
    });
    //获取省市
    function GetOpenProvince() {
        var paramaters = {
            "jiBie": 1
        };
        var openProvinceInfo = $('#openProvince');
        //openProvinceInfo.append('<option value="" selected="selected">--省市--</option>');
        $.ajax({
            type: 'POST',
            url: '/BusinessManager/GetOpenProvince',
            data: paramaters,
            success: function (result) { 
                $.each(result, function (i, item) {
                    openProvinceInfo.append('<option value="' + item.Code + '">' + item.Name + '</option>');
                });
            }
        }); 
    }
    //选择省时触发联动
    $('#openProvince').change(function () {
        //alert($('#openProvince').val() + ":::" + $('#openProvince').find("option:selected").text());
        LoadOpenCityInfo();
    });
    //获取市区
    function LoadOpenCityInfo() {
        //var parrentId = $()
        var paramaters = {
            "jiBie": 2,
            "parrentId": $('#openProvince').val()
    };
        var openCityInfo = $('#openCity');
        openCityInfo.empty();
        //openCityInfo.append('<option value="" selected="selected">--市区--</option>');
        $.ajax({
            type: 'POST',
            url: '/BusinessManager/GetOpenCity',
            data: paramaters,
            success: function (result) {
                $.each(result, function (i, item) {
                    openCityInfo.append('<option value="' + item.Code + '">' + item.Name + '</option>');
                });
            }
        });
    }

    
    //设置结算比例-外送费
    $("#btnCommissionConfim").on('click', function () {
        var busCommissionHid = $("#busCommissionHid").val(); //商户id

        var busCommissionText = $("#busCommissionText").val(); ////商户结算比例
        var busCommissionWaiSong = $("#busCommissionWaiSong").val(); ////商户外送费
        var oldBusCommissionText = $("#oldBusCommissionText").val(); ////原来商户结算比例
        var oldBusCommissionWaiSong = $("#oldBusCommissionWaiSong").val(); ////原来商户外送费
        var commissionFixValue = $("#CommissionFixValue").val();
        var oldCommissionFixValue = $("#oldCommissionFixValue").val(); ////原来商户外送费
        var strategyID = $("#businessGroupID").val();
        var oldStrategyID = $("#oldStrategyID").val();
        var commissionType = $('input[name="rCommissionFormulaMode"]:checked').val();
        var oldBusCommissionWaiSong = $("#oldBusCommissionWaiSong").val();
        var oldCommissionType = $('#oldCommissionType').val();
        if (isNaN(busCommissionText)) {
            alert("请输入正确的数字!");
            return;
        }
        if (busCommissionText < 0) {
            alert("请输入大于零的值!");
            return;
        }

        if (isNaN(busCommissionWaiSong)) {
            alert("请输入正确的数字!");
            return;
        }
        if (busCommissionWaiSong < 0) {
            alert("请输入大于零的值!");
            return;
        }

        if (busCommissionText == oldBusCommissionText && busCommissionWaiSong == oldBusCommissionWaiSong && commissionFixValue == oldCommissionFixValue && strategyID == oldStrategyID && oldCommissionType == commissionType) {
            alert("没有需要修改的信息!");
            return;
        }
        if (busCommissionWaiSong.length == 0) {
            busCommissionWaiSong = 0;
        }
        var paramaters = { "id": busCommissionHid, "commission": busCommissionText, "waisongfei": busCommissionWaiSong, "commissionType": commissionType, "commissionFixValue": commissionFixValue, "strategyID": strategyID };
        var url = "/BusinessManager/SetCommission";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
                if (result.IsSuccess) {
                    alert("设置成功!");
                    window.location.href = "/BusinessManager/BusinessManager";
                } else {
                    alert(result.Message);
                }
            }
        });
    });
    //修改商家信息
    $("#btnUpdateBusinessInfo").on('click', function () {

        var busiId = $("#busiId").val(); //商户id
        var busiName = $("#busiName").val(); //商户电话
        var busiPhone = $("#busiPhone").val(); //商户电话
        var busiSourceId = $("#busiSourceId").val(); //商户原平台Id
        var busiMealsSettleMode = $("#busiMealsSettleMode").val(); ////商户集团Id
        var busGroupId = $("#busGroupId").val(); ////商户集团Id
        var oldBusiSourceId = $("#oldBusiSourceId").val(); //商户原平台Id
        var oldBusGroupId = $("#oldBusGroupId").val(); ////商户集团Id
        if (busiName.trim().length == 0) {
            alert("请输入商家名称!");
            return;
        }
        var reg = /^0?1\d{10}$/;
        if (!reg.test(busiPhone)) {
            alert("请输入正确的手机号!");
            return;
        }
        if (busiSourceId.trim().length == 0) {
            busiSourceId = 0;
        }
        if (busGroupId <= 0) {
            busGroupId = 0;
        }
        var paramaters = {
            "id": busiId,
            "businessName": busiName,
            "businessPhone": busiPhone,
            "businessSourceId": busiSourceId,
            "groupId": busGroupId,
            "oldBusiSourceId": oldBusiSourceId,
            "oldBusGroupId": oldBusGroupId,
            "mealsSettleMode": busiMealsSettleMode
        };
        var url = "/BusinessManager/ModifyBusiness";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
                if (result.IsSuccess) {
                    alert("修改成功!");
                    window.location.href = "/BusinessManager/BusinessManager";
                } else {
                    alert(result.Message);
                }
            }
        });
    });
    //添加商户
    $("#btnAddBusiness").on('click', function () {
        var businessName = $("#businessName").val(); //商户
        var businessCommission = $("#businessCommission").val(); ////商户结算比例
        var businessphoneNo = $("#businessphoneNo").val(); ////商户手机号
        var businessaddr = $("#businessaddr").val(); ////商户地址
        var businesspassWord = $("#businesspassWord").val(); ////商户密码
        var businesscityid = $("#businesscity").val(); ////商户城市id
        var businesscity = $("#businesscity").find("option:selected").text(); ////商户城市
        var txtGroupId = $("#addbusinessGroupID").val(); ////商户集团
        var businessWaisong = $("#businessWaisong").val(); ////商户外送费

        if (businessName == null || businessName == "") {
            alert("请输入商户名!");
            return;
        }
        if (businessphoneNo == null || businessphoneNo == "") {
            alert("请输入商户手机号!");
            return;
        }
        if (businessaddr == null || businessaddr == "") {
            alert("请输入商户地址!");
            return;
        }
        if (businesspassWord == null || businesspassWord == "") {
            alert("请输入商户密码!");
            return;
        }
        if (businesscityid == null || businesscityid == "") {
            alert("请选择商户城市!");
            return;
        }
        if (txtGroupId == null || txtGroupId == "") {
            txtGroupId = 0;
        }

        if (isNaN(businessCommission)) {
            alert("请输入正确的数字!");
            return;
        }
        if (businessCommission < 0) {
            alert("请输入大于零的值!");
            return;
        }
        if (isNaN(businessWaisong)) {
            alert("请输入正确的数字!");
            return;
        }
        if (businessWaisong < 0) {
            alert("请输入大于零的值!");
            return;
        }
        var paramaters = { "GroupId": txtGroupId, "businessaddr": businessaddr, "businessWaisong": businessWaisong, "businessCommission": businessCommission, "passWord": businesspassWord, "city": businesscity, "CityId": businesscityid, "businessName": businessName, "phoneNo": businessphoneNo };
        var url = "/BusinessManager/AddBusiness";
        $.ajax({
            type: 'POST',
            url: url,
            data: paramaters,
            success: function (result) {
                if (result.IsSuccess) {
                    alert("设置成功!");
                    window.location.href = "/BusinessManager/BusinessManager";
                } else {
                    alert(result.Message);
                }
            }
        });
    });

    function showaddBusiness() {
        adminjs.openwinbox('#BusinessAddDiv');
    }

    //商户充值
    $("#btnRechargeCommit").on('click', function () {
        var busiId = $("#busId").val(); //商户id
        var busiName = $("#busName").val(); //商户电话
        var busiRechargeAmount = $("#busRechargeAmount").val(); //商户充值金额
        var rechargeLog = $("#rechargeLog").val(); //充值描述
        if (rechargeLog.trim().length == 0) {
            alert("请输入充值操作描述!");
            return;
        }
        var decimalFormat = /^[0-9]*(\.[0-9]{1,2})?$/;
        if (!decimalFormat.test(busiRechargeAmount)) {
            alert("请输入正确的金额！");
            return;
        }
        if (busiRechargeAmount < 1 || busiRechargeAmount > 50000) {
            alert("充值金额须在1-50000元之间！");
            return;
        }
        if (confirm("确定要为商户：" + busiName + "  充值：" + busiRechargeAmount + "元？")) {
            var paramaters = {
                "BusinessId": busiId,
                "RechargeAmount": busiRechargeAmount,
                "Remark": rechargeLog
            };
            var url = "/BusinessManager/BusinessRecharge";
            $.ajax({
                type: 'POST',
                url: url,
                data: paramaters,
                success: function (result) {
                    if (result.IsSuccess) {
                        alert(result.Message);
                        window.location.href = "/BusinessManager/BusinessManager";
                    } else {
                        alert(result.Message);
                    }
                }
            });
        }
    });

    //商户提现
    $("#btnWithdrawCommit").on('click', function () {
        var withdrawId = $("#withdrawId").val(); //商户id
        var busiName = $("#withdrawPhone").val(); //商户电话
        var withdrawAmount = $("#withdrawAmount").val();
        var withdrawLog = $("#withdrawLog").val();
        var selectProvinceName = $("#openProvince").val();
        var selectProvinceCode = $("#openProvinceCode").val();
        var selectCityName = $("#openCity").val();
        var selectCityCode = $("#openCityCode").val();
        var idCard = $("#idCard").val();
        //if (rechargeLog.trim().length == 0) {
        //    alert("请输入充值操作描述!");
        //    return;
        //} 
        var decimalFormat = /^[0-9]*(\.[0-9]{1,2})?$/;
        if (!decimalFormat.test(withdrawAmount)) {
            alert("请输入正确的金额！");
            return;
        }
        if (withdrawAmount < 1 || withdrawAmount > 1000000) {
            alert("请输入正确的提款金额，大于1元小于1,000,000！");
            return;
        }

        if (confirm("是否确认提款?")) {
            var paramaters = {
                "BusinessId": withdrawId,
                "WithdrawPrice": withdrawAmount,
                "Remarks": withdrawLog,
                "OpenProvinceCode": selectProvinceCode,
                "OpenProvince": selectProvinceName,
                "selectCityCode": selectCityCode,
                "OpenCity": selectCityName,
                "idCard": idCard
            };
            var url = "/BusinessManager/Withdraw";
            $.ajax({
                type: 'POST',
                url: url,
                data: paramaters,
                success: function (result) {

                    if (result.Status == "1") {
                        alert(result.Message);
                        window.location.href = "/BusinessManager/BusinessManager";
                    } else {
                        alert(result.Message);
                    }
                }
            });
        }
    });
    //关闭弹层
    $('.J_closebox').click(function () {
        adminjs.closewinbox('.add-openbox');
        return false;
    });
    $('input[name="rCommissionFormulaMode"]').click(function () {
        var a = $('input[name="rCommissionFormulaMode"]:checked').val();
        if (a == 1) {
            $("#divCommissionFixValue").hide();
            $("#divbusCommissionText").show();
        } else {
            $("#divbusCommissionText").hide();
            $("#divCommissionFixValue").show();
        }
    });
    
</script>
