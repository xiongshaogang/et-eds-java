<%
/* *
 *功能：纯网关接口接入页
 *版本：3.3
 *日期：2012-08-14
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *************************注意*****************
 *如果您在接口集成过程中遇到问题，可以按照下面的途径来解决
 *1、商户服务中心（https://b.alipay.com/support/helperApply.htm?action=consultationApply），提交申请集成协助，我们会有专业的技术工程师主动联系您协助解决
 *2、商户帮助中心（http://help.alipay.com/support/232511-16307/0-16307.htm?sh=Y&info_type=9）
 *3、支付宝论坛（http://club.alipay.com/read-htm-tid-8681712.html）
 *如果不想使用扩展功能请把扩展功能参数赋空值。
 **********************************************
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.edaisong.business.pay.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%@page import="com.edaisong.business.common.UserContext"%>
<%@page import="com.edaisong.core.util.OrderNoHelper"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝纯网关接口</title>
	</head>
	<%
    UserContext context = UserContext.getCurrentContext(request);
	
	if(request.getParameter("WIDtotal_fee")==null||
		request.getParameter("WIDdefaultbank")==null||
		context==null){
		%>充值金额或支付方式没有传递或没有登录<%
	}else{
		////////////////////////////////////请求参数//////////////////////////////////////
		String basePath =PropertyUtils.getProperty("static.business.url");
		String groupInfo="集团商家["+context.getBusinessName()+"("+context.getBusinessID()+")]充值";
		//支付类型
		String payment_type = "1";
		//必填，不能修改
		//服务器异步通知页面路径
		String notify_url = basePath+"/group/return_url";
		//需http://格式的完整路径，不能加?id=123这类自定义参数
		//页面跳转同步通知页面路径
		String return_url = basePath+"/group/return_url";
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
		//商户订单号
		String out_trade_no =new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//商户网站订单系统中唯一订单号，必填
		//订单名称
		String subject =groupInfo;// new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
		//必填
		//订单描述
		String body ="集团商家充值";// new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
		//默认支付方式
		String paymethod = "bankPay";
		//必填
		//默认网银
		String defaultbank = new String(request.getParameter("WIDdefaultbank").getBytes("ISO-8859-1"),"UTF-8");
		//必填，银行简码请参考接口技术文档
		
		//付款金额
		String total_fee = new String(request.getParameter("WIDtotal_fee").getBytes("ISO-8859-1"),"UTF-8");
		//必填
		//商品展示地址
		String show_url =basePath;// new String(request.getParameter("WIDshow_url").getBytes("ISO-8859-1"),"UTF-8");
		//需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html
		//防钓鱼时间戳
		String anti_phishing_key = AlipaySubmit.query_timestamp();
		//若要使用请调用类文件submit中的query_timestamp函数
		//客户端的IP地址
		String exter_invoke_ip = request.getRemoteAddr();
		//非局域网的外网IP地址，如：221.0.0.1
		
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_email", AlipayConfig.seller_email);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		if(defaultbank.equals("alipay")){
			sParaTemp.put("paymethod", "directPay");
			//sParaTemp.put("defaultbank", defaultbank);
		}else{
			sParaTemp.put("paymethod", paymethod);
			sParaTemp.put("defaultbank", defaultbank);
		}

		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		out.println(sHtmlText);
		}
	%>
	<body>
	</body>
</html>
