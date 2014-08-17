<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.emp.model.*"%>
<%
	String path = request.getContextPath();
%>


<div id="backmain" class="col-md-10">
	<h1 class="page-header">員工帳號新增</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">帳號新增</h3>
		</div>
		<div class="panel-body">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<p class="red">請修正以下錯誤:</p>
				<ul class="red">
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<form method="post" action="<%=path%>/EmpServlet" name="form1">
				<div class="table-responsive">
					<table class="table table-bordered">
						<tr>
							<th>帳號:</th>
							<td><input type="text" name="empacc" value="${empVO2.empacc}"  placeholder="請輸入帳號" 
								required></td>
						</tr>
						<tr>
							<th>姓名:</th>
							<td><input type="text" name="empname"
								value="${empVO2.empname}" placeholder="請輸入姓名" required></td>
						</tr>
						<tr>
							<th>信箱:</th>
							<td><input type="text" name="empemail"
								value="${empVO2.empemail}" placeholder="請輸入信箱 "  required></td>

						</tr>
						<tr>
							<th>地址:</th>
							<td><textarea rows="1" cols="50" name="empaddr">${empVO2.empaddr}</textarea></td>

						</tr>
						<tr>
							<th>身分證號:</th>
							<td><input type="text" name="empid" value="${empVO2.empid}" placeholder="請輸入身分證號"
								required></td>


						</tr>
						<tr>
							<th>電話:</th>
							<td><input type="text" name="emptel"
								value="${empVO2.emptel}" placeholder="請輸入電話" required></td>
						</tr>
					</table>
				</div>
				<input type="hidden" name="action" value="insert"> <input
					class="btn btn-warning btn-lg" type="submit" value="確定新增">
			</form>
		</div>
	</div>
</div>