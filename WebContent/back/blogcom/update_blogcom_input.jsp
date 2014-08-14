<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.blogcom.model.*"%>
<%
	BlogcomVO blogcomVO = (BlogcomVO) request.getAttribute("blogcomVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>網誌留言資料修改 - update_blogcom_input.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body>


	<h3>資料修改:</h3>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/BlogcomServlet">
		<table border="0">
			<tr>
				<td>網誌留言編號:</td>
				<td><%=blogcomVO.getBgcomno()%></td>
			</tr>
			<tr>
				<td>網誌編號:</td>
				<td><%=blogcomVO.getBlogno()%></td>
			</tr>
			<tr>
				<td>會員編號:</td>
				<td><%=blogcomVO.getMemno()%></td>
			</tr>
			<tr>
				<td>留言內容:</td>
				<td><input type="TEXT" name="bgcomtext" size="45"
					value="<%=blogcomVO.getBgcomtext()%>" /></td>
			</tr>
			<tr>
				<td>發布時間:</td>
				<td><%=blogcomVO.getBgcomtime()%></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="bgcomno" value="<%=blogcomVO.getBgcomno()%>">
		<input type="hidden" name="blogno" value="<%=blogcomVO.getBlogno()%>">
		<input type="hidden" name="memno" value="<%=blogcomVO.getMemno()%>">
		<input type="hidden" name="bgcomtime"
			value="<%=blogcomVO.getBgcomtime()%>"> <input type="submit"
			value="送出修改">
	</FORM>

</body>
</html>
