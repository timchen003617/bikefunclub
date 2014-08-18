<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.photo.model.*"%>
<%@ page import="com.bikefunclub.album.model.*"%>
<%@ page import="com.bikefunclub.gpalbum.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
// AlbumVO albumVO =(AlbumVO)request.getAttribute("albumVO");
// AlbumService albumSvc = new AlbumService();
// List<PhotoVO> listPohto = albumSvc.getAlbno(albumVO.getAlbno());
	List<PhotoVO> list = (List<PhotoVO>)request.getAttribute("listPohto");
	AlbumVO albumVO = (AlbumVO)request.getAttribute("albumVO");
	MemVO memVO = (MemVO)session.getAttribute("memVO");
%>

<div class="container body-content">
	<div class="row">
		<div class="col-md-12">
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

	<table class="table table-hover">
		<tr>
			<th>拍攝地點</th>
			<th>相片上傳時間</th>
			<th>相片檔案</th>
			<td><FORM METHOD="post" action="<%=request.getContextPath()%>/front/photo/page_addPhoto.jsp">
			     <input type="submit" value="新增相片">
			     <input type="hidden" name="albno" value="${albumVO.albno}">
			</FORM></td>
			<td><FORM METHOD="post" action="<%=request.getContextPath()%>/front/albcls/page_listAllAlbcls.jsp">
			     <input type="submit" value="回到分類相簿">
			     <input type="hidden" name="albno" value="${albumVO.albno}">
			</FORM></td>

		</tr>
		
		<%@ include file="page1A.file"%>
		<c:forEach var="photoVO" items="${listPohto}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle'>
				<td>${photoVO.phcoo}</td>
				<td>${photoVO.phup}</td>
				<td><img
					src="<%=request.getContextPath()%>/PhotoPreViewServlet?photono=${photoVO.photono}"
					width='100' hieght='100'></td>

<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						action="<%=request.getContextPath()%>/PhotoServlet"> --%>
<!-- 						<input type="submit" value="修改"> <input type="hidden" -->
<%-- 							name="photono" value="${photoVO.photono}"> <input --%>
<!-- 							type="hidden" name="action" value="getOne_For_Update"> <input -->
<!-- 							type="hidden" name="requestURL" -->
<%-- 							value="<%=request.getServletPath()%>"> <input --%>
<%-- 							type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 					</FORM> -->
<!-- 				</td> -->
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
		</div>
	</div>
</div>




