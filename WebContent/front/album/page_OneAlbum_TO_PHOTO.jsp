<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
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
<link href="<%=path%>/fancybox/jquery.fancybox.css?v=2.1.5" rel="stylesheet">
<script src="<%=path%>/js/jquery-1.11.1.min.js"></script>
<script src="<%=path%>/fancybox/jquery.fancybox.js?v=2.1.5"></script>
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

</head>
<body>
	<!-- slider -->
	<jsp:include page="/front/home/slider.jsp"></jsp:include>
	<!-- header -->
	<jsp:include page="/front/home/header.jsp"></jsp:include>
	<!-- navbar -->
	<jsp:include page="/front/home/navbar.jsp"></jsp:include>
	<!--main-->
	<jsp:include page="OneAlbum_TO_PHOTO.jsp"></jsp:include>
	<!-- footer -->
	<jsp:include page="/front/home/footer.jsp"></jsp:include>
</body>
</html>