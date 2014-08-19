<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>管理員後台</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=path%>/img/favicon.ico" rel="icon" media="screen">
<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="<%=path%>/css/admin.css" rel="stylesheet" media="screen">
<link href="<%=path%>/css/jquery-ui.css" rel="stylesheet" media="screen">
<link href="<%=path%>/css/accordion.css" rel="stylesheet" media="screen">
<script src="<%=path%>/js/jquery-1.11.1.min.js"></script>
<script src="<%=path%>/js/bootstrap.min.js"></script>
<script src="<%=path%>/js/jquery-ui.js"></script>
<script src="<%=path%>/js/accordion.js"></script>
</head>

<body>
	<!-- top -->
	<jsp:include page="/back/home/top.jsp"></jsp:include>
	<div id="backmaincontain" class="container-fluid">
		<div id="backmainrow" class="row-fluid">
			<!-- sidebar -->
			<jsp:include page="/back/home/sidebar.jsp"></jsp:include>
			<!-- main -->
			<jsp:include page="update_blogcls_input.jsp"></jsp:include>
<%--             <% --%>
<!-- // 				if (request.getAttribute("listrots_Byrotclsno")!=null){ -->
<%-- 			%> --%>
<%-- 			<jsp:include page="listRots_Byrotclsno.jsp" /> --%>
<%-- 			<% --%>
<!-- // 				} -->
<%-- 			%> --%>
		</div>
	</div>
</body>
</html>
