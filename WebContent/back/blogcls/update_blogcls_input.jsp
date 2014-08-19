<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.rotcls.model.*" %>
<%@ page import="com.bikefunclub.blogcls.model.*"%>
<%
	String path = request.getContextPath();
    BlogclsVO blogclsVO = (BlogclsVO) request.getAttribute("blogclsVO");
%>
<div id="backmain" class="col-md-10">
	<h1 class="page-header">網誌分類修改</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">網誌分類修改</h3>
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
			<FORM METHOD="post" ACTION="BlogclsServlet" name="form1">
				<div class="table-responsive">
					<table class="table table-bordered">
						<tr>
							<th>網誌分類編號:</th>
							<td>${blogclsVO.blogclsno}</td>
						</tr>
						<tr>
							<th>路線分類名稱:</th>
							<td><input type="TEXT" name="blogclsname" size="45"
								value="${blogclsVO.blogclsname}" /></td>
						</tr>
					</table>
				</div>

				<input type="hidden" name="action" value="update"> <input
					type="hidden" name="blogclsno" value="${blogclsVO.blogclsno}"> <input
					type="hidden" name="blogclsname" value="${blogclsVO.blogclsname}">
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