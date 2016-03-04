<%@page import="com.edaisong.entity.domain.GroupTodayStatistics"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.edaisong.entity.BusinessMessage"%>
<%@page import="com.edaisong.entity.domain.BusinessOrderSummaryModel"%>
<%@page
	import="com.edaisong.entity.domain.BusiPubOrderTimeStatisticsModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
	String basePath = PropertyUtils.getProperty("java.business.url");
	BusinessOrderSummaryModel b= (BusinessOrderSummaryModel)request.getAttribute("b");
	List<BusiPubOrderTimeStatisticsModel> bdaysNew = 
	(List<BusiPubOrderTimeStatisticsModel>)request.getAttribute("bdaysNew");
	List<BusiPubOrderTimeStatisticsModel> bdaysDelivery = 
	(List<BusiPubOrderTimeStatisticsModel>)request.getAttribute("bdaysDelivery");
	List<BusiPubOrderTimeStatisticsModel> bdaysTaking = 
	(List<BusiPubOrderTimeStatisticsModel>)request.getAttribute("bdaysTaking");
	List<BusiPubOrderTimeStatisticsModel> bdaysComplite = 
		(List<BusiPubOrderTimeStatisticsModel>)request.getAttribute("bdaysComplite");
%>
<div class="filters u-4">
	<div class="u1 f1 active" idx="0">
		<div class="icon-wrap"></div>
		<p><%=b.getUnReceive()%></p>
		<p>待接单</p>
	</div>
	<div class="u1 f2" idx="1">
		<div class="icon-wrap"></div>
		<p><%=b.getReceived()%></p>
		<p>取货中</p>
	</div>
	<div class="u1 f3" idx="2">
		<div class="icon-wrap"></div>
		<p><%=b.getPickUp()%></p>
		<p>配送中</p>
	</div>
	<div class="u1 f4" idx="3">
		<div class="icon-wrap"></div>
		<p><%=b.getFinish()%></p>
		<p>已完成</p>
	</div>
</div>
<div class="chart" style="width: 100%; height: 300px;"></div>


 <script>
	var bdaysNew = new Array();
	var bdaysDelivery = new Array();
	var bdaysTaking = new Array();
	var bdaysComplite = new Array();
<%
		StringBuilder sbbdaysNew = new StringBuilder();
		for(BusiPubOrderTimeStatisticsModel model : bdaysNew){
			sbbdaysNew.append(String.format("bdaysNew[%d]=%d;", model.getHour(),model.getPubCount()));
		}
		%>
<%=sbbdaysNew.toString()%>

<%
	StringBuilder sbbdaysDelivery = new StringBuilder();
	for(BusiPubOrderTimeStatisticsModel model : bdaysDelivery){
		sbbdaysDelivery.append(String.format("bdaysDelivery[%d]=%d;", model.getHour(),model.getPubCount()));
	}
	%>
<%=sbbdaysDelivery.toString()%>

<%
	StringBuilder sbbdaysTaking = new StringBuilder();
	for(BusiPubOrderTimeStatisticsModel model : bdaysTaking){
		sbbdaysTaking.append(String.format("bdaysTaking[%d]=%d;", model.getHour(),model.getPubCount()));
	}
	%>
<%=sbbdaysTaking.toString()%>

<%
	StringBuilder sbbdaysComplite = new StringBuilder();
	for(BusiPubOrderTimeStatisticsModel model : bdaysComplite){
		sbbdaysComplite.append(String.format("bdaysComplite[%d]=%d;", model.getHour(),model.getPubCount()));
	}
	%>
<%=sbbdaysComplite.toString()%>

	var hours = [];
	var counts1 = [];
	var counts2 = [];
	var counts3 = [];
	var counts4 = [];
	for (i = 0; i < 24; i++) {
		var c = i + 1;
		hours[i] = (c).toString();
		if (!bdaysNew[c]) {
			counts1[i] = 0;
		} else {
			counts1[i] = bdaysNew[c];
		}
		if (!bdaysDelivery[c]) {
			counts2[i] = 0;
		} else {
			counts2[i] = bdaysDelivery[c];
		}
		if (!bdaysTaking[c]) {
			counts3[i] = 0;
		} else {
			counts3[i] = bdaysTaking[c];
		}
		if (!bdaysComplite[c]) {
			counts4[i] = 0;
		} else {
			counts4[i] = bdaysComplite[c];
		}
	};
        var statistics = [{
            name: '待接单',
            data: counts1
        }, {
            name: '取货中',
            data: counts2
        }, {
            name: '配货中',
            data: counts3
        }, {
            name: '已完成',
            data: counts4
        }];
        

    
    $(function() {
		function t(t) {
			for (var i = $(".chart").highcharts().series, s = i.length; s >= 1;)
				if (s--, i[s].name == statistics[t - 0].name)
					return i[s].remove(!1)
		}
		function i() {
			$(".chart").highcharts().redraw()
		}
		
		statistics[0].color = "#00aac1", statistics[0].id = 0;
		statistics[1].color = "#7cb23b", statistics[1].id = 1;
		statistics[2].color = "#e88900", statistics[2].id = 2;
		statistics[3].color = "#d9534f", statistics[3].id = 3;
		var s = {
			title : {
				text : "今日订单统计",
				x : -20
			},
			exporting : {
				enabled : !1
			},
			xAxis : {
				categories : [ "1时", "2时", "3时", "4时", "5时", "6时", "7时", "8时",
						"9时", "10时", "11时", "12时", "13时", "14时", "15时", "16时",
						"17时", "18时", "19时", "10时", "21时", "22时", "23时", "24时" ]
			},
			yAxis : {
				title : {
					text : "订单量"
				},
				plotLines : [ {
					value : 0,
					width : 1,
					color : "#808080"
				} ]
			},
			tooltip : {
				valueSuffix : "单"
			},
			legend : {
				layout : "vertical",
				align : "right",
				verticalAlign : "middle",
				borderWidth : 0
			},
			series : [ statistics[0],statistics[1],statistics[2],statistics[3] ]
		};
		$(".chart").highcharts(s),
		$(".u1").click(
				function () {
					var s = $(this);
					if (s.hasClass("active")) 
					{ 
						if (!($(".filters .active").size() > 1))
							return;
						s.removeClass("active"), t(s.attr("idx"))
						} else s.addClass("active"), $(".chart").highcharts().addSeries(statistics[$(this).attr("idx") - 0]); i() })
	});
</script>