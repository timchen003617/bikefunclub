<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.gpalbum.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
	GpalbumService gpalbumSvc = new GpalbumService();
	List<GpalbumVO> list = gpalbumSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>所有相片所屬相簿 - listAllGpalbum.jsp</title>
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
			<th>相片編號</th>

		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="gpalbumVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle'>
				<td>${gpalbumVO.albno}</td>
				<td>${gpalbumVO.photono}</td>
				<td>
					<FORM METHOD="post"
						action="<%=request.getContextPath()%>/GpalbumServlet">
						<input type="submit" value="刪除"> <input type="hidden"
							name="albno" value="${gpalbumVO.albno}"> <input
							type="hidden" name="photono" value="${gpalbumVO.photono}">
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>
