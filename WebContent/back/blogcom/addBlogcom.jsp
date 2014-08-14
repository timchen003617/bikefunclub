<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.blogcom.model.*"%>

<html>
<head>
<title>網誌留言資料新增 - addBlogcom.jsp</title></head>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/BlogcomServlet">
<table border="0">

	<tr>
		<td>網誌編號:</td>
		<td><input type="TEXT" name="blogno" size="45"
		     value="${param.blogno}" /></td><td>${errorMsgs.blogno}</td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memno" size="45"
		     value="${param.memno}" /></td><td>${errorMsgs.memno}</td>
	</tr>
	<tr>
		<td>留言內容:</td>
		<td><input type="TEXT" name="bgcomtext" size="45"
		     value="${param.bgcomtext}" /></td><td>${errorMsgs.bgcomtext}</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
