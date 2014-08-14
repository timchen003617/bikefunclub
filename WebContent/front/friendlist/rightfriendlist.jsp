<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String pathuri = request.getRequestURI();
%>
<div class="col-md-8">
	<ol class="breadcrumb">
		<li class="active"><a href="<%=pathuri%>">好友管理</a></li>
	</ol>
	<div class="tabbedPanels">
		<ul class="tabs">
			<li><a href="#panel1" onclick="setTabNum(0)">好友管理</a></li>
			<li><a href="#panel2" onclick="setTabNum(1)">新增好友</a></li>
		</ul>
		<div class="tab_container">
			<!--所有好友-->
			<div class="tab_content" id="panel1">
				<jsp:include page="allfrilist.jsp"></jsp:include>
			</div>
			<!--查詢未成為好友的所有會員和自己-->
			<div class="tab_content" id="panel2">
			<jsp:include page="notfrimemlist.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>