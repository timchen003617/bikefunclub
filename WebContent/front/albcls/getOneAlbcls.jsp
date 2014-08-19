<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.album.model.*"%>
<%@ page import="com.bikefunclub.albcls.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
	AlbclsVO albclsVO;
	if (request.getAttribute("listAlbum") != null) {
		albclsVO = (AlbclsVO) request.getAttribute("albclsVO");
	} else {
		AlbclsService albclsSvc = new AlbclsService();
		albclsVO = (AlbclsVO) request.getAttribute("albclsVO");
	}
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
	pageContext.setAttribute("albclsVO", albclsVO);

	AlbumService albSvc = new AlbumService();
	List<AlbumVO> list = albSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<div class="container body-content">
	<div class="row">
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<span class='red'>請修正以下錯誤:</span>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<%@ include file="page1.file"%>
		<table>
			<tr>
				<th>相簿分類名稱</th>
				<th>相簿名稱</th>
				<th>權限</th>
				<th>相簿描述</th>
				<th>建立時間</th>
				<th>檢視相片</th>
			</tr>

			<jsp:useBean id="albclsSvc" scope="page"
				class="com.bikefunclub.albcls.model.AlbclsService" />
			<c:forEach var="albumVO" items="${listAlbum}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<tr align='center' valign='middle'>
					<td>${albclsSvc.getAlbclsname(albumVO.albclsno)}</td>
					<td>${albumVO.albtitle}</td>
					<td>${albumVO.authname}</td>
					<td>${albumVO.albdesc}</td>
					<td>${albumVO.albtime}</td>

					<td>
						<form method="post"
							action="<%=request.getContextPath()%>/AlbumServlet">
							<input type="submit" value="檢視相片"> <input type="hidden"
								name="albno" value="${albumVO.albno}"> <input
								type="hidden" name="memno" value="${memVO.memno}"> <input
								type="hidden" name="action" value="GET_ALBNO_TO_PHOTO">
							<input type="hidden" name="requestURL"
								value="<%=request.getServletPath()%>"> <input
								type="hidden" name="whichPage" value="<%=whichPage%>">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
</div>
