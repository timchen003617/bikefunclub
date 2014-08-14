<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.albcls.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
    AlbclsService albclsSvc = new AlbclsService();
    List<AlbclsVO> list = albclsSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>所有相簿分類 - listAllAlbcls.jsp</title>
</head>
<body>

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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>相簿分類編號</th>
		<th>相簿分類名稱</th>
		<th>檢視相簿分類</th>

	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="albclsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${albclsVO.albclsno}</td>
			<td>${albclsVO.albclsname}</td>
			<td>
			  <FORM METHOD="post" action="<%=request.getContextPath()%>/AlbclsServlet">
			     <input type="submit" value="檢視相簿分類">
			     <input type="hidden" name="albclsno" value="${albclsVO.albclsno}">
			     <input type="hidden" name="action"	value="getOneAlbcls_For_Display"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" action="<%=request.getContextPath()%>/AlbclsServlet">
			     <input type="submit" value="修改">
			     <input type="hidden" name="albclsno" value="${albclsVO.albclsno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" action="<%=request.getContextPath()%>/AlbclsServlet">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="albclsno" value="${albclsVO.albclsno}">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath() %>">
			    <input type="hidden" name="whichPage" value="<%=whichPage%>">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
