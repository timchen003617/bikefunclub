<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.ad.model.*"%>
<%@ page import="com.bikefunclub.Ann.model.*"%>
<%@ page import="com.bikefunclub.Ann.controller.*"%>
<%@ page import="com.bikefunclub.emp.model.*"%>
<%
	String path = request.getContextPath();
	AnnVO annVO = (AnnVO) request.getAttribute("annVO");
%>

<div id="backmain" class="col-md-10">
	<h1 class="page-header">公告修改</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">公告修改</h3>
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
			<form method="post" action="AnnServlet" name="form"
				enctype="multipart/form-data">
				<div class="table-responsive">
					<table class="table table-bordered">
						<tr>
							<th>公告編號</th>
							<td>${annVO.annno}</td>
						</tr>
						<tr>
							<th>公告標題</th>
							<td><input type="text" name="anntitle" size="110"
								value="${annVO.anntitle}" required></td>
						</tr>
						<tr>
							<th>公告內容</th>
							<td><textarea rows="10" cols="109" name="anncontent">${annVO.anncontent}</textarea>
						</tr>
						<tr>
							<th>公告日期</th>
							<td><fmt:formatDate value="${annVO.anndate}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<th>公告圖片</th>
							<td>
								<div id="preview">
										<img id="imgheadmd" class="ann"
											src="<%=path%>/AnnreadimgServlet?annno=${annVO.annno}">
								</div> <br> <label for="InputFile">${annVO.annfilename}.${annVO.annextname}</label>
								<input type="file" id="InputFile" name="adfile"
								value="${annVO.annfilename}.${annVO.annextname}"
								onchange="previewImage(this,'imgheadmd')">
							</td>
						</tr>
					</table>
				</div>
				<input type="hidden" name="action" value="update"> <input
					type="hidden" name="annno" value="${annVO.annno}"> <input
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