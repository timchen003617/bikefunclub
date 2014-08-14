<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.gpcls.model.*"%>
<%
	String path = request.getContextPath();
	GpclsVO gpclsVO = (GpclsVO) request.getAttribute("gpclsVO");
%>
<div id="backmain" class="col-md-10">
	<h1 class="page-header">揪團分類修改</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">揪團分類修改</h3>
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
			<FORM METHOD="post" ACTION="Gpcls.do" name="form1">
				<div class="table-responsive">
					<table class="table table-bordered">
						<tr>
							<th>揪團分類編號:</th>
							<td>${param.gpclsno}</td>
						</tr>
						<tr>
							<th>揪團分類名稱:</th>
							<td><input type="TEXT" name="gpclsname" size="45"
								value="${gpclsVO.gpclsname}" /></td>
						</tr>
					</table>
				</div>

				<input type="hidden" name="action" value="update"> <input
					type="hidden" name="gpclsno" value="${param.gpclsno}"> <input
					type="hidden" name="gpclsname" value="${gpclsVO.gpclsname}">
				<input type="hidden" name="requestURL"
					value="<%=request.getParameter("requestURL")%>">
				<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
				<input type="hidden" name="whichPage"
					value="<%=request.getParameter("whichPage")%>">
				<input class="btn btn-warning btn-lg" type="submit" value="送出修改">
			</FORM>
		</div>
	</div>
</div>