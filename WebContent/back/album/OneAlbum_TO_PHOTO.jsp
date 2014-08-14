<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.photo.model.*"%>
<%@ page import="com.bikefunclub.album.model.*"%>
<%@ page import="com.bikefunclub.gpalbum.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
// AlbumVO albumVO =(AlbumVO)request.getAttribute("albumVO");
// AlbumService albumSvc = new AlbumService();
// List<PhotoVO> listPohto = albumSvc.getAlbno(albumVO.getAlbno());
	List<PhotoVO> list = (List<PhotoVO>)request.getAttribute("listPohto");
	request.getAttribute("albumVO");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>所有相片 - OneAlbum_TO_Photo.jsp</title>
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
		
		<%@ include file="page1A.file"%>
		<c:forEach var="photoVO" items="${listPohto}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle'>
				<td>${photoVO.photono}</td>
				<td>${photoVO.memno}</td>
				<td>${photoVO.phcoo}</td>
				<td>${photoVO.phup}</td>
				<td><img
					src="<%=request.getContextPath()%>/PhotoPreViewServlet?photono=${photoVO.photono}"
					width='300' hieght='300'></td>

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
						action="<%=request.getContextPath()%>/AlbumServlet">
						<input type="submit" value="刪除"> 
						<input type="hidden" name="photono" value="${photoVO.photono}"> 
						<input type="hidden" name="albno" value="${albumVO.albno}">
						<input type="hidden" name="action" value="GET_ALBNO_TO_Delete"> 
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2A.file"%>

</body>
</html>
