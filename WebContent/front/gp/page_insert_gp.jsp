<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>發起揪團</title>
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
<link href="<%=path%>/js/jquery-ui-timepicker-addon.css" rel="stylesheet"></link>
<script src="<%=path%>/js/jquery-1.11.1.min.js"></script>
<script src="<%=path%>/js/bootstrap.min.js"></script>
<script src="<%=path%>/js/jquery-ui.js"></script>
<script src="<%=path%>/js/nav1.1.min.js"></script>
<script src="<%=path%>/dwr/engine.js"></script>
<script src="<%=path%>/dwr/util.js"></script>
<script src="<%=path%>/dwr/interface/Chat.js"></script>
<script src="<%=path%>/js/dropdownmenu.js"></script>
<script src="<%=path%>/js/slider.js"></script>
<script src="<%=path%>/js/chat.js"></script>
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script src="<%=path%>/js/googlemap_dialog.js"></script>
<script src="<%=path%>/js/jquery-ui-timepicker-addon.js"></script>
<script src="<%=path%>/js/datetimepicker.js"></script>
<script src="<%=path%>/js/jquery.validate.min.js"></script>
<script src="<%=path%>/js/gpvalidate.js"></script>
<script src="<%=path%>/js/magicbutton.js"></script>
</head>
<body>

	<!-- slider -->
	<jsp:include page="/front/home/slider.jsp"></jsp:include>
	<!-- header -->
	<jsp:include page="/front/home/header.jsp"></jsp:include>
	<!-- navbar -->
	<jsp:include page="/front/home/navbar.jsp"></jsp:include>
	<!--main -->
	<jsp:include page="insert_gp.jsp"></jsp:include>
	<!-- footer -->
	<jsp:include page="/front/home/footer.jsp"></jsp:include>
</body>
</html>