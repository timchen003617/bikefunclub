<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.photo.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();
	PhotoService photoSvc = new PhotoService();
	List<PhotoVO> list = photoSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="memSvc" class="com.bikefunclub.member.model.MemService" />
<jsp:useBean id="gpalbumSvc" class="com.bikefunclub.gpalbum.model.GpalbumService" />
<jsp:useBean id="albumSvc" class="com.bikefunclub.album.model.AlbumService" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div id="backmain" class="col-md-10">
	<h1 class="page-header">相片管理</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">相片管理</h3>
		</div>
		<div class="panel-body">

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
				<p class="red">請修正以下錯誤:</p>
				<ul class="red">
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		<%@ include file="pages/page1.file"%>
	<div class="table-responsive">
<table id="photo" class="table table-hover">
<thead>
		<tr>
			<th>相片編號</th>
			<th>相簿標題</th>
			<th>會員姓名</th>
<!-- 			<th>拍攝地點</th> -->
			<th>相片上傳時間</th>
			<th>相片檔案</th>
			<th>相片刪除</th>
		</tr>
</thead>
 	<tbody>

		<c:forEach var="photoVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr ${(photoVO.photono==param.photono)? 'style="background-color:#f5f0e9;"' : ''}>
				<td>${photoVO.photono}</td>
				<td><c:forEach var="gpalbumVO" items="${gpalbumSvc.all}">
				<c:if test="${gpalbumVO.photono==photoVO.photono}">
				<c:forEach var="albumVO" items="${albumSvc.all}">
				<c:if test="${gpalbumVO.albno==albumVO.albno}">
						${albumVO.albtitle}</c:if>	</c:forEach></c:if></c:forEach></td>
						
				<td><c:forEach var="memVO" items="${memSvc.all}">
				<c:if test="${memVO.memno==photoVO.memno}">
						${memVO.memname}</c:if>	</c:forEach></td>
<%-- 				<td>${photoVO.phcoo}</td> --%>
				<td>${photoVO.phup}</td>
				<td><img
					src="<%=request.getContextPath()%>/PhotoPreViewServlet?photono=${photoVO.photono}"
					width='300' height='300'></td>

<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						action="<%=request.getContextPath()%>/PhotoServlet"> --%>
<!-- 						<input class="btn btn-warning" type="submit" value="修改"> <input type="hidden" -->
<%-- 							name="photono" value="${photoVO.photono}"> <input --%>
<!-- 							type="hidden" name="action" value="getOne_For_Update"> <input -->
<!-- 							type="hidden" name="requestURL" -->
<%-- 							value="<%=request.getServletPath()%>"> <input --%>
<%-- 							type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 					</FORM> -->
<!-- 				</td> -->
				<td>
					<FORM METHOD="post"
						action="<%=request.getContextPath()%>/PhotoServlet">
						<input class="btn btn-warning" type="submit" value="刪除"> <input type="hidden"
							name="photono" value="${photoVO.photono}"> <input
							type="hidden" name="action" value="back_delete"> <input
							type="hidden" name="requestURL"
							value="<%=request.getServletPath()%>"> <input
							type="hidden" name="whichPage" value="<%=whichPage%>">
					</FORM>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<%@ include file="pages/page2.file"%>
</div>
</div>
</div>
