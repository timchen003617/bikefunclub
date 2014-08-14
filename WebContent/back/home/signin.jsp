<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
    String errorLogin = (String) request.getAttribute("errorLogin");
%>
<!DOCTYPE html>
<html>
<head>
<title>員工登入</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="<%=path%>/img/favicon.ico" rel="icon" media="screen">
<link href="<%=path%>/css/signin.css" rel="stylesheet" media="screen">

</head>

<body>

	<div class="container">
		<h1 class="logo">
			<a href="<%=path%>/back/home/signin.jsp">BikeFunclub</a>
		</h1>
		<form  id="Emplogin" class="form-signin" action="<%=path%>/EmpLoginHandler" 
method="post">
			<h2 class="form-signin-heading">管理員登入</h2>
			<input type="text" class="form-control" placeholder="帳號" name="account"
				required autofocus> 
			<input type="password" name="password" class="form-control" placeholder="密碼" required>
			<div class="checkbox">
<!-- 				<label> <input type="checkbox" value="remember-me"> -->
<!-- 					記得我 -->
<!-- 				</label> -->
				<c:if test="${not empty errorLogin}">
					<p class="red">
						<span class="glyphicon glyphicon-exclamation-sign"></span>帳號或密碼無效!
					</p>
				</c:if>
			</div>
			<button class="btn btn-lg btn-warning btn-block" type="submit">
				登入</button>
		</form>

	</div>
	<!-- /container -->

</body>
</html>