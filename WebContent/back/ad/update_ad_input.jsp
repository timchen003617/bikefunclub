<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.ad.model.*"%>
<!-- 	AdServlet forward 過來的request key:adVO -->
<%
	String path = request.getContextPath();
%>
<div id="backmain" class="col-md-10">
	<h1 class="page-header">廣告輪播管理</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">廣告輪播管理</h3>
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
			<form method="post" action="AdServlet" name="form"
				enctype="multipart/form-data">
				<div class="table-responsive">
					<table class="table table-bordered">
						<tr>
							<th>廣告編號</th>
							<td>${adVO.adno}</td>
						</tr>
						<tr>
							<th>廣告標題</th>
							<td><input type="text" name="adname" size="40"
								value="${adVO.adname}" required></td>
						</tr>
						<tr>
							<th>廣告連結</th>
							<td><input type="text" name="adurl" size="40"
								value="${adVO.adurl}" required></td>
						</tr>
						<tr>
							<th>廣告內容</th>
							<td><textarea rows="10" cols="50" name="adesc">${adVO.adesc}</textarea></td>
						</tr>
						<tr>
							<th>廣告圖片</th>
							<td>								
								<div id="preview">
								<img id="imgheadmd" src="<%=path%>/CarouselServlet?adno=${adVO.adno}">		
								</div> <br> <label for="InputFile">${adVO.filename}.${adVO.extname}</label>
								<input type="file" id="InputFile" name="adfile"
								value="${adVO.filename}.${adVO.extname}" onchange="previewImage(this,'imgheadmd')">
							</td>
						</tr>
					</table>
				</div>
				<input type="hidden" name="action" value="update"> <input
					type="hidden" name="adno" value="${adVO.adno}"> <input
					type="hidden" name="requestURL"
					value="<%=request.getAttribute("requestURL")%>">
				<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
				<input type="hidden" name="whichPage"
					value="<%=request.getAttribute("whichPage")%>">
				<!--只用於:listAllAd.jsp-->
				<input class="btn btn-warning btn-lg" type="submit" value="確定修改">
			</form>
		</div>
	</div>
</div>