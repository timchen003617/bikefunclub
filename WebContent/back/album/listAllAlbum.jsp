<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.album.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
    AlbumService albumSvc = new AlbumService();
    List<AlbumVO> list = albumSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>所有相簿 - listAllAlbcls.jsp</title>
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
		<th>相簿編號</th>
		<th>會員編號</th>
		<th>相簿分類編號</th>
		<th>權限名稱</th>
		<th>相簿標題</th>
		<th>相簿描述</th>
		<th>建立時間</th>
		<th>檢視相簿</th>

	</tr>
	<%@ include file="page1.file" %>  
	<c:forEach var="albumVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${albumVO.albno}</td>
			<td>${albumVO.memno}</td>
			<td>${albumVO.albclsno}</td>
			<td>${albumVO.authname}</td>
			<td>${albumVO.albtitle}</td>
			<td>${albumVO.albdesc}</td>
			<td>${albumVO.albtime}</td>

			<td>
			  <FORM METHOD="post" action="<%=request.getContextPath()%>/AlbumServlet">
			     <input type="submit" value="檢視相簿">
			     <input type="hidden" name="albno" value="${albumVO.albno}">
			     <input type="hidden" name="action"	value="GET_ALBNO_TO_PHOTO"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" action="<%=request.getContextPath()%>/AlbumServlet">
			     <input type="submit" value="修改">
			     <input type="hidden" name="albno" value="${albumVO.albno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" action="<%=request.getContextPath()%>/AlbumServlet">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="albno" value="${albumVO.albno}">
			    <input type="hidden" name="action"value="delete">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath() %>">
			    <input type="hidden" name="whichPage" value="<%=whichPage %>"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
