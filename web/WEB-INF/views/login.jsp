<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">		
<%@include file="common/head.jsp" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrapValidator.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/base.css">
<title>Login</title>
</head>
<body>	
		<div id="mycontainer" class="container">			
			<div id="mypanel" class="panel panel-default">
					<div class="panel-heading">
					<a href="#login-view" data-toggle="tab"><h3>登录</h3></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#signup-view" data-toggle="tab"><h3>注册</h3></a>
					</div>
					<div class="tab-content">
					<div id="login-view" class="panel-body tab-pane fade in active">
						<form id="loginForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/loginCheck">
							<div class="form-group">
								<label for="username" class="col-sm-2 control-label">
								<span class="glyphicon glyphicon-user" aria-hidden="true"></span></label>
								<div class="col-sm-10">
									<input type="text" class="form-control myform" name="username" id="username" placeholder="请输入用户名">
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="col-sm-2 control-label">
								<span class="glyphicon glyphicon-eye-close" aria-hidden="true">							
								</label>
								<div class="col-sm-10">
									<input type="password" class="form-control myform" name="password" id="password" placeholder="请输入密码">
								</div>
							</div>
							<div class="form-group">
								<label for="loginbtn" class="col-sm-2 control-label"></label>
								<div class=".col-sm-10">
									<button id="loginbtn" type="submit" class="btn btn-primary myform">登录</button>
								</div>
							</div>
						</form>
					</div>
					<div id="signup-view" class="panel-body tab-pane fade" >
						<form id="signUpForm" class="form-horizontal">
							<div class="form-group">
								<div class="col-sm-10 registab">
									<input type="text" class="form-control myform" id="name" name="name" placeholder="姓名(长度3-18位)">
								</div>						
							</div>
							<div class="form-group">								
								<div class="col-sm-10 registab">
									<label class="radio-inline"><input type="radio" name="optradio" >男</label>
									<label class="radio radio-inline"><input type="radio" name="optradio" >女</label>
								</div>								
							</div>
							<div class="form-group">								
								<div class="col-sm-10 registab">
									<input type="text" class="form-control myform" id="phone" name="phone" placeholder="手机号">
								</div>								
							</div>
							<div class="form-group">
								<div class="col-sm-10 registab">
									<input type="email" class="form-control myform" id="sign_email" name="sign_email" placeholder="邮箱">
								</div>								
							</div>
							<div class="form-group">								
								<div class="col-sm-10 registab">
									<input type="password" class="form-control myform" id="pwd" name="pwd" placeholder="密码(字母+数字，长度6-18位)">
								</div>								
							</div>
							<div class="form-group">								
								<div class="col-sm-10 registab">
									<input type="password" class="form-control myform" id="confirm_pwd" name="confirm_pwd" placeholder="确认密码">
								</div>								
							</div>
							<div class="form-group">								
								<div class=".col-sm-10 registab">
									<button id="signupbtn" type="submit" class="btn btn-primary myform">注册</button>
								</div>
							</div>
						</form>
					</div>
					</div>
			</div>				
		</div>
	</body>
		<script src="${pageContext.request.contextPath}/static/js/jquery-2.2.4.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/dist/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/bootstrapValidator.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/registerValidator.js"></script>	
		<script type="text/javascript">
		$(document).ready(function(){			
			$("#btn").click(function(){
				var name=$("#username").val();
				var pwd=$("#pwd").val();				
				$.ajax({
					type: "POST",
				    url: "${pageContext.request.contextPath}/loginCheck",				  
				    dataType:'json',
				    data: {'username':name,"password":pwd},
				    success: function(result) {
				        
				    }
				});
			});
			
		});
	</script>
</html>