<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.emp.model.*" %>
<% 
    String path = request.getContextPath();
    EmpVO empVO = (EmpVO) session.getAttribute("empVO");
    pageContext.setAttribute("empVO", empVO);
%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div id="back" class="container-fluid">
		<div class="navbar-header"></div>
		<div class="navbar-collapse collapse">
			<h3 class="logo">
				<a href="<%=path%>/back/ann/page_listAllAnn.jsp">Bikefunclub</a>
			</h3>
			<h3 style="letter-spacing: 3px; font-weight:bold;">單車俱樂部後台管理系統</h3>
			<p class="text-right">
				<strong>${empVO.empname},你好!</strong> 
				<a href="<%=path%>/EmpLoginOut">登出</a>
			</p>

		</div>
	</div>
</div>