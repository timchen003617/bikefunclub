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
<title>會員登入</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="<%=path%>/img/favicon.ico" rel="icon" media="screen">
<link href="<%=path%>/css/style.css" rel="stylesheet" media="screen">
</head>

<body>

	<div class="container">
		<h1 class="loginlogo">
			<a href="<%=path%>/front/home/index.jsp">BikeFunclub</a>
		</h1>
		<form id="login" class="form-signin" action="<%=path%>/LoginHandler"
			method="post">
			<input type="text" class="form-control" name="account"
				placeholder="帳號" required autofocus> <input type="password"
				class="form-control" name="password" placeholder="密碼" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					保持登入狀態
				</label>
				<c:if test="${not empty errorLogin}">
					<p class="red">
						<span class="glyphicon glyphicon-exclamation-sign"></span>${errorLogin}
					</p>
				</c:if>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">
				登入</button>
			<div class="text-center">或</div>
		</form>
		<div class="register">
			<a class="btn btn-lg btn-primary btn-block"
				href="<%=path%>/front/mem/memregister.jsp"> 註冊成為會員</a>
		</div>
	</div>
	<!-- /container -->

</body>
</html>