<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.blogcls.model.*"%>
<%
    BlogclsVO blogclsVO = (BlogclsVO) request.getAttribute("blogclsVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)

%>
<html>
<head>
<title>網誌分類資料修改 - update_blogcls_input.jsp</title></head>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/BlogclsServlet"> 
<table border="0">
	<tr>
		<td>網誌分類編號:<font color=red><b>*</b></font></td>
		<td><%=blogclsVO.getBlogclsno() %></td>
	</tr>
	
	<tr>
		<td>網誌分類名稱:</td>
		<td><input type="TEXT" name="blogclsname" size="45" 
			 value="<%=blogclsVO.getBlogclsname() %>"/></td>
	</tr>
	
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="blogclsno" value="<%=blogclsVO.getBlogclsno()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
