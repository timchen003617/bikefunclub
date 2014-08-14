<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.gpalbum.model.*"%>

<html>
<head>
<title>相片所屬相簿資料新增 - addGpalbum.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body>


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
		ACTION="<%=request.getContextPath()%>/GpalbumServlet">
		<table border="0">

			<tr>
				<td>相簿編號:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="albno" size="45"
					value="${param.albno}" /></td>
				<td>${errorMsgs.albno}</td>
			</tr>
			<tr>
				<td>相片編號:<font color=red><b>*</b></font></td>
				<td><input type="TEXT" name="photono" size="45"
					value="${param.photono}" /></td>
				<td>${errorMsgs.albno}</td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
</body>

</html>
