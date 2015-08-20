<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.edaisong.business.common.ServerUtil" %>
<%@page import="com.edaisong.core.util.PropertyUtils"%>
<%
String basePath =PropertyUtils.getProperty("static.business.url");
%>

<%
	boolean isLogin = ServerUtil.checkIsLogin(request,response);
	if(isLogin){
		//如果登录,跳转到首页
		response.sendRedirect(request.getContextPath()+"/index");
	}
%>

<!DOCTYPE html>
	<html>
		<head>
			<title>E代送登入注册</title>
			<meta charset="utf-8" />
			<link type="text/css" rel="stylesheet" href="<%=basePath %>/css/login.css">
			<script type="text/javascript" src="<%=basePath %>/js/jquery-1.11.2.min.js"></script>
			<script type="text/javascript" src="<%=basePath %>/js/global.js"></script>
		</head>
		<body>
			<div class="ex_box">
				<div class="ex_top">
					<p><img src="<%=basePath %>/images/ex_logo.png" alt="" /></p>
				</div>
				<div class="ex_cont">
					<div class="cont_left">
						<img src="<%=basePath %>/images/ex_banner.png" alt="" />
					</div>
					<div class="cont_right">
						<div class="ex_error"><p id="error"></p></div>
						<div class="right_for">
							<h3>商铺帐号登录</h3>
								<input type="text" placeholder="输入手机号码" class="ex_iphone" maxlength="11" name="phoneNo" id="phoneNo">
								<p><b class="error error1"></b></p>
								<input type="password" placeholder="输入密码"  class="ex_pord" name="password" id="password">
								<p><b class="error error2"></b></p>
								<input type="text" placeholder="输入验证码" maxlength="4" class="ex_get" name="code" id="code"><span class="ex_over"><img id="imgCode" src="<%=basePath %>/account/code?x=Math.random();" class="img"></span>
								<p><b class="error error3"></b></p>
								<label><input type="checkbox" checked="checkbox" name="rememberMe" id="rememberMe"/>记住我（下次自动登录）</label> 
								<input type="submit" value="登&nbsp;&nbsp;录" class="ex_submit" id="btnLogin">
							<dl>第一次来E代送？<a href="###">快速注册</a></dl>
						</div>
					</div>
				</div>
				<div class="ex_foot">
					<div class="footer">
						<p class="footer_left"><img src="<%=basePath %>/images/ex_iphone.png" /></p>
						<p class="footer_right"><span>京ICP备15014116号-1 Copyright©2011-2015</span><span>易代送网络科技（北京）有限公司,All rights reserved.</span></p>
					</div>				
				</div>
			</div>	
			
			<script type="text/javascript">
				$("#btnLogin").click(function(){
					var flag =  iphone();
					flag = flag || password();
					flag = flag || get();
					if(flag){
						var url = "<%=basePath %>/account/login";
						var params = {
								"phoneNo":$("#phoneNo").val(),
							  	"password":$("#password").val(),
							  	"code":$("#code").val(),
							  	"rememberMe":$("#rememberMe").val()
							  };
						//请求接口
 					    $.ajax({
							url:url,
							data:params,
							type:"POST",
							async:true,
							success:function(data){
								if(data.success){
									window.location.href = "<%=basePath %>/index";
								}else{
									$("#error").text(data.message);
									$("#error").show();
									changeCodeImg();
									$("#password").val("");
									$("#code").val("");
								}
							},
							error:function(error){
								alert(error);
							},
						});
					}		
					return false;

				})
				
				$("#imgCode").click(function(){
					var ran = Math.random();
					this.src = "<%=basePath %>/account/code?x="+ran;
				})
				
				function changeCodeImg(){
					var ran = Math.random();
					document.getElementById("imgCode").src = "<%=basePath %>/account/code?x="+ran;
				}
				
				
				//以下代码是检测是否有鼠标移动,将用于用户登录后是否有操作
				/*var oldX = 0;
				var oldY = 0;
				var timeOutSeconds = 5;
				window.lastMove = 0;
				var getCoordInDocumentExample = function(){
				    document.onmousemove = function(e){
				      var pointer = getCoordInDocument(e);
				      if(Math.abs(pointer.x-oldX) > 1 || Math.abs(pointer.y-oldY)>1){
				    	  //鼠标已经移动,证明正在操作
				    	  lastMove = new Date().getTime();
				      }
				      oldX = pointer.x;
				      oldY = pointer.y;
				    }
				  }
				  var getCoordInDocument = function(e) {
				    e = e || window.event;
				    var x = e.pageX || (e.clientX +
				      (document.documentElement.scrollLeft
				      || document.body.scrollLeft));
				    var y= e.pageY || (e.clientY +
				      (document.documentElement.scrollTop
				      || document.body.scrollTop));
				    return {'x':x,'y':y};
				  }
				  window.onload = function(){
				     getCoordInDocumentExample();
				     setInterval(function(){
				    	 var now = new Date().getTime();
				    	 if((now - lastMove) > (timeOutSeconds * 1000)){
				    		 //alert("已超时");
				    		 //window.location.href = "<%=basePath %>/";
				    	 }
				     },1000);
				 };*/
			</script>			
		</body>
	</html>	