<%@ page import="com.bikefunclub.ad.model.AdService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>

<div id ="backmain" class="col-md-10">
	<h1 class="page-header">資料查詢:</h1>
	<ul>

		<jsp:useBean id="repSvc" scope="page"
			class="com.bikefunclub.report.model.ReportService" />

		<li>
			<form method="post" action="<%=path%>/report.do">
				<p>輸入檢舉日期:</p>
				<span>日期: </span> <input type="text" id="startDate" name="startDate"
					maxDate="endDate" readonly> <span>&nbsp;–&nbsp;</span> <input
					type="text" id="endDate" name="endDate" minDate="startDate"
					readonly> <input type="submit" value="送出"> <input
					type="hidden" name="action" value="getOne_For_Display">
			</form>
		</li>
		<li><a href='listAllReport.jsp'>清單</a>所有檢舉資料</li>
	</ul>


	<h3>檢舉管理</h3>

	<ul>
		<li><a href="addReport.jsp">新增</a> a new Emp.</li>
	</ul>
</div>
