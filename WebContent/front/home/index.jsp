<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String status = (String) request.getParameter("success");
	pageContext.setAttribute("success", status);
%>
<!DOCTYPE html>
<html>
<head>
<title>Bikefunclub</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=path%>/img/favicon.ico" rel="icon" media="screen">
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="<%=path%>/css/jquery-ui.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="<%=path%>/css/style.css">
<link rel="stylesheet" href="<%=path%>/css/navigation.css">
<link rel="stylesheet" href="<%=path%>/css/slider.css">
<script src="<%=path%>/js/jquery-1.11.1.min.js"></script>
<script src="<%=path%>/js/bootstrap.min.js"></script>
<script src="<%=path%>/js/jquery-ui.js"></script>
<script src="<%=path%>/js/nav1.1.min.js"></script>
<script src="<%=path%>/dwr/engine.js"></script>
<script src="<%=path%>/dwr/util.js"></script>
<script src="<%=path%>/dwr/interface/Chat.js"></script>
<script src="<%=path%>/js/messages.js"></script>
<script src="<%=path%>/js/dropdownmenu.js"></script>
<script src="<%=path%>/js/tabs.js"></script>
<script src="<%=path%>/js/slider.js"></script>
<script src="<%=path%>/js/chat.js"></script>
<script src="<%=path%>/js/dialog.js"></script>
<%-- 會員資料修改成功 --%>
<c:if test="${success=='success'}">
<script src="<%=path%>/js/dialogopen.js"></script>
</c:if>
<%-- 信箱認證成功 --%>
<c:if test="${getmailstatus=='success'}">
<script src="<%=path%>/js/dialogopenmail.js"></script>
</c:if>
</head>
<body>
	<div id="memmdok" title="訊息" style="display:none">
		<p>會員資料修改成功，請重新登入!</p>
	</div>
	<div id="mailok" title="訊息" style="display:none">
		<p>信箱認證成功!</p>
	</div>
	<!-- slider -->
	<jsp:include page="slider.jsp"></jsp:include>
	<!-- header -->
	<jsp:include page="header.jsp"></jsp:include>
	<!-- navbar -->
	<jsp:include page="navbar.jsp"></jsp:include>
	<!--carousel -->
	<jsp:include page="carousel.jsp"></jsp:include>
	<!--main-->
	<jsp:include page="main.jsp"></jsp:include>
	<!-- footer -->
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>