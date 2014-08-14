<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.gpcls.model.*"%>

<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();
    GpclsService gpclsSvc = new GpclsService();
    List<GpclsVO> list = gpclsSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div id="backmain" class="col-md-10">
	<h1 class="page-header">揪團分類管理</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">揪團分類管理</h3>
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
			<%@ include file="pages/page1.file"%>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>揪團分類編號</th>
							<th>揪團分類名稱</th>
							<th>修改</th>
							<th>刪除</th>
							<th>查詢此分類揪團</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="gpclsVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr
								${(gpclsVO.gpclsno==param.gpclsno)? 'style="background-color:#f5f0e9;"' : ''}>
								<!--將修改的那一筆加入對比色而已-->
								<td>${gpclsVO.gpclsno}</td>
								<td>${gpclsVO.gpclsname}</td>
								<td>
									<FORM METHOD="post" ACTION="<%=contextpath%>/Gpcls.do">
										<input class="btn btn-warning" type="submit" value="修改"> <input type="hidden"
											name="gpclsno" value="${gpclsVO.gpclsno}"> <input
											type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post" ACTION="<%=contextpath%>/Gpcls.do">
										<input class="btn btn-warning" type="submit" value="刪除"> 
										<input type="hidden" name="gpclsno" value="${gpclsVO.gpclsno}"> 
										<input type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="delete">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post" ACTION="<%=contextpath%>/Gpcls.do">
										<input class="btn btn-warning" type="submit" value="查詢"> <input type="hidden"
											name="gpclsno" value="${gpclsVO.gpclsno}"> <input
											type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action"
											value="getGps_FromGpclsno_ForGpcls">
									</FORM>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<%@ include file="pages/page2.file"%>
		</div>
	</div>
</div>
