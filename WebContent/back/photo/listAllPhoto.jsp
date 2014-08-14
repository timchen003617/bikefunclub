<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.photo.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
	PhotoService photoSvc = new PhotoService();
	List<PhotoVO> list = photoSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>所有相片 - listAllPhoto.jsp</title>
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
			<th>相片編號</th>
			<th>會員編號</th>
			<th>拍攝地點</th>
			<th>相片上傳時間</th>
			<th>相片檔案</th>

		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="photoVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle'>
				<td>${photoVO.photono}</td>
				<td>${photoVO.memno}</td>
				<td>${photoVO.phcoo}</td>
				<td>${photoVO.phup}</td>
				<td><img
					src="<%=request.getContextPath()%>/PhotoPreViewServlet?photono=${photoVO.photono}"
					width='300' height='300'></td>

				<td>
					<FORM METHOD="post"
						action="<%=request.getContextPath()%>/PhotoServlet">
						<input type="submit" value="修改"> <input type="hidden"
							name="photono" value="${photoVO.photono}"> <input
							type="hidden" name="action" value="getOne_For_Update"> <input
							type="hidden" name="requestURL"
							value="<%=request.getServletPath()%>"> <input
							type="hidden" name="whichPage" value="<%=whichPage%>">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						action="<%=request.getContextPath()%>/PhotoServlet">
						<input type="submit" value="刪除"> <input type="hidden"
							name="photono" value="${photoVO.photono}"> <input
							type="hidden" name="action" value="delete"> <input
							type="hidden" name="requestURL"
							value="<%=request.getServletPath()%>"> <input
							type="hidden" name="whichPage" value="<%=whichPage%>">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>
