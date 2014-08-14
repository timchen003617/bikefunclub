<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.gp.model.*"%>
<%
	String path = request.getContextPath();
	GpVO gpVO = (GpVO) request.getAttribute("gpVO");
%>
<div id="backmain" class="col-md-10">
	<h1 class="page-header">修改揪團分類</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">修改揪團分類</h3>
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
							<th>揪團編號</th>
							<th>揪團名稱</th>
							<th>揪團分類名稱</th>
						</tr>
						<tr>
							<td>${gpVO.gpno}</td>
							<td>${gpVO.gptitle}</td>

							<jsp:useBean id="gpclsSvc" scope="page"
								class="com.bikefunclub.gpcls.model.GpclsService" />

							<td><select size="1" name="gpclsno">
									<c:forEach var="gpclsVO" items="${gpclsSvc.all}">
										<option value="${gpclsVO.gpclsno}"
											${(gpVO.gpclsno==gpclsVO.gpclsno)?'selected':'' }>${gpclsVO.gpclsname}
									</c:forEach>
							</select></td>
						</tr>
					</table>
				</div>

				<input type="hidden" name="action" value="update_Gpcls_FromGp">
				<input type="hidden" name="gpno" value="${gpVO.gpno}">
				<input type="hidden" name="gpclsno" value="${gpVO.gpclsno}">
				<input type="hidden" name="whichPage1" value="<%=request.getParameter("whichPage1")%>">
				<input type="hidden" name="requestURL"
					value="<%=request.getParameter("requestURL")%>">
				<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
<input class="btn btn-warning btn-lg" type="submit" value="送出修改">
			</FORM>
		</div>
	</div>
</div>