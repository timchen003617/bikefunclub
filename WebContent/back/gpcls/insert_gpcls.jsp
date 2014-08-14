<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.gpcls.model.*"%>
<%
	GpclsVO gpclsVO = (GpclsVO) request.getAttribute("gpclsVO");
	String path = request.getContextPath();  
%>
<div id="backmain" class="col-md-10">
	<h1 class="page-header">新增揪團分類</h1>  
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">新增揪團分類</h3>
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
			<FORM METHOD="post" ACTION="<%=path%>/Gpcls.do" name="form1">
				<div class="table-responsive">
					<table class="table table-bordered">
						<tr>
							<th>新增揪團名稱:</th>
							<td><input type="TEXT" name="gpclsname" size="45"
								value="<%=(gpclsVO == null) ? "啾啾團" : gpclsVO.getGpclsname()%>" /></td>
						</tr>
					</table>
				</div>
				<br> <input type="hidden" name="action" value="insert">
				<input class="btn btn-warning btn-lg" type="submit" value="送出新增">
			</FORM>
		</div>
	</div>
</div>