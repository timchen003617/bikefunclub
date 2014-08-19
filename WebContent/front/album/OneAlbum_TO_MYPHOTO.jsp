<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.photo.model.*"%>
<%@ page import="com.bikefunclub.album.model.*"%>
<%@ page import="com.bikefunclub.gpalbum.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
	List<PhotoVO> list = (List<PhotoVO>) request
			.getAttribute("listPohto");
	AlbumVO albumVO = (AlbumVO) request.getAttribute("albumVO");
	MemVO memVO = (MemVO) session.getAttribute("memVO");
%>

<div class="container body-content">
	<div class="row">
		<div class="col-md-12">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<span class="red">請修正以下錯誤:</span>
				<ul class="red">
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<table class="table table-hover">
				<tr>
					<th>相片上傳時間</th>
					<th>相片檔案</th>
					<th><form method="post"
							action="<%=request.getContextPath()%>/front/photo/page_addmyPhoto.jsp">
							<input type="submit" value="新增相片"> <input type="hidden"
								name="albno" value="${albumVO.albno}">
						</form></th>
				</tr>
				<%@ include file="page1A.file"%>
				<c:forEach var="photoVO" items="${listPohto}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td><fmt:formatDate value="${photoVO.phup}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><img
							src="<%=request.getContextPath()%>/PhotoPreViewServlet?photono=${photoVO.photono}"
							width='100' height='100'></td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="page2A.file"%>
		</div>
	</div>
</div>




