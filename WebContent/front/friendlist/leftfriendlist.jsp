<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>

<div class="col-md-4">
	<!-- Nav tabs -->
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<span class="glyphicon glyphicon-user"></span>好友名單查詢
			</h3>
		</div>
		<div class="panel-body">
			<form class="friname" method="post">
				<p>
					<strong>好友姓名:</strong><input class="form-control" type="text" id="friname" name="friname"
						placeholder="search" value="${friname}"> 
				</p>
				<input type="hidden" id="actionfri" name="action"
					value="getfriname_For_Display">
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">
				<span class="glyphicon glyphicon-user"></span>會員名單查詢
			</h3>
		</div>
		<div class="panel-body">
			<form class="memname" method="post">
				<p>
					<strong>會員姓名:</strong><input class="form-control" type="text" id="memname" name="memname"
						placeholder="search" value="${memname}">
				</p>
				<input type="hidden" id="actionmem" name="action"
					value="getmemname_For_Display">
			</form>
		</div>
	</div>
</div>