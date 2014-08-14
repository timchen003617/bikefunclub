<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.emp.model.*"%>
<%
	String path = request.getContextPath();
%>


<div id="backmain" class="col-md-10">
	<h1 class="page-header">網誌新增</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">網誌新增</h3>
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
							<th>會員編號:</th>
							<td><input type="text" name="memno" value="${blogVO2.memno}"
								placeholder="請輸入會員邊號" required></td>
						</tr>
						<tr>

							<th>網誌分類分類編號:</th>
							<td><input type="text" name="blogclsno"
								value="${blogVO2.blogclsno}" required></td>
							<%-- 								<td>${errorMsgs.blogclsno}</td> --%>

						</tr>
						<tr>
							<th>網誌標題:</th>
							<td><input type="text" name="bgtitle"
								value="${blogVO2.bgtitle}" placeholder="請輸入網誌標題" required></td>
						</tr>
						<tr>
							<th>網誌內容:</th>
							<td><textarea rows="3" cols="30">${empVO2.empemail}</textarea></td>


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
				<input type="hidden" name="action" value="insert"> <input
					type="submit" value="確定新增">
			</form>
		</div>
	</div>
</div>