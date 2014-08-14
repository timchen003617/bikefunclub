<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.blogcls.model.*"%>


<html>
<head>
<title>網誌分類資料新增 - addBlogcls.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body>


	<h4>
		網誌分類名稱:<font color=red><b>*</b></font>為必填欄位
	</h4>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message.value}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/BlogclsServlet">
		<table border="0">

			<tr>
				<td>網誌分類名稱:</td>
				<td><input type="TEXT" name="blogclsname" size="45"
					value="${param.blogclsname}" /></td>
				<td>${errorMsgs.blogclsname}</td>

			</tr>


		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>

</html>
