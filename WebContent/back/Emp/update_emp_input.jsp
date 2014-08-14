<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.emp.model.*" %>
<!-- 	EmpServlet forward 過來的request key:EmpVO -->
<%  
   EmpVO empVO = (EmpVO) request.getAttribute("empVO");
   String path = request.getContextPath(); 
   
  
  
%>

<div id="backmain" class="col-md-10">
	<h1 class="page-header">員工基本資料修改</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">基本資料修改</h3>
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
			<form method="post" action="EmpServlet" name="form"
				>
				<div class="table-responsive">
					<table class="table table-bordered">
						<tr>
							<th>編號:</th>
							<td>${empVO.empno}</td>
						</tr>
						<tr>
							<th>帳號:</th>
							<td><input type="text" name="empacc" value="${empVO.empacc}" required></td>
						</tr>
<!-- 						<tr> -->
<!-- 							<th>密碼:</th> -->
<%-- 							<td>${empVO.emppw}</td> --%>
<!-- 						</tr> -->
						<tr>
							<th>姓名:</th>
							<td><input type = "text" name="empname" value="${empVO.empname}" required></td>
						</tr>
						<tr>
							<th>信箱:</th>
							<td><input type="text" name="empemail" value="${empVO.empemail}" required></td>
						</tr>
						<tr>
							<th>地址:</th>
							<td><textarea rows="1" cols="50 "name="empaddr">${empVO.empaddr}</textarea>
						</tr>
						<tr>
							<th>身分證號:</th>
							<td><input type="text" name="empid" value="${empVO.empid}" required></td>
						</tr>
						<tr>
							<th>註冊日期:</th>
							<td>${empVO.emprgdate}</td>
						</tr>
						<tr>
							<th>電話:</th>
							<td><input type="text" name="emptel" value="${empVO.emptel}" required></td>
						</tr>
						
					</table>
				</div>
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="empno" value="${empVO.empno}">
				<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>">
				<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
				<input type="hidden" name="whichPage"
					value="<%=request.getAttribute("whichPage")%>">
				<!--只用於:listAllAd.jsp-->
				<input class="btn btn-warning btn-lg" type="submit" value="確定修改">
			</form>
		</div>
	</div>
</div>