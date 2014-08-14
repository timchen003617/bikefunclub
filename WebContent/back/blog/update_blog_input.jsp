<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.blog.model.*"%>
<!-- 	EmpServlet forward 過來的request key:EmpVO -->
<%
	BlogVO blogVO = (BlogVO) request.getAttribute("blogVO");
	String path = request.getContextPath();
%>

<div id="backmain" class="col-md-10">
	<h1 class="page-header">網誌修改</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">網誌修改</h3>
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
			<form method="post" action="BlogServlet" name="form">
				<div class="table-responsive">
					<table class="table table-bordered">
						<tr>
							<th>會員編號:</th>
							<td>${blogVO.memno}</td>
						</tr>
						<tr>
							<th>網誌分類編號:</th>
							<td><input type="text" name="bgtitle"
								value="${blogVO.blogclsno}" required></td>
						</tr>
						<tr>
							<th>網誌標題:</th>
							<td><input type="text" name="bgtitle"
								value="${blogVO.bgtitle}" required></td>
						</tr>
						<tr>
							<th>網誌內容:</th>
							<td><input type="text" name="bgtext"
								value="${blogVO.bgtext}" required></td>
						</tr>
						<tr>
							<th>權限名稱:</th>
							<td><select name="authname" size="1">
									<option value="Personal">個人</option>
									<option value="Sharefs">分享好友</option>
									<option value="Public">公開</option>
							</select></td>
                      </tr>


					</table>
				</div>
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="empno" value="${blogVO.blogno}">
				<input type="hidden" name="requestURL"
					value="<%=request.getAttribute("requestURL")%>">
				<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
				<input type="hidden" name="whichPage"
					value="<%=request.getAttribute("whichPage")%>">
				<!--只用於:listAllAd.jsp-->
				<input type="submit" value="確定修改">
			</form>
		</div>
	</div>
</div>