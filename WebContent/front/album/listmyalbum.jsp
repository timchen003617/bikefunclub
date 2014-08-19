<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%@ page import="com.bikefunclub.album.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
	MemVO loginmemVO = (MemVO) session.getAttribute("memVO");
	Integer loginmemno = loginmemVO.getMemno();

	AlbumService albumSvc = new AlbumService();
	List<AlbumVO> list;
	List<AlbumVO> listalbcls = (List<AlbumVO>) request
			.getAttribute("albumVO");

	if (listalbcls != null) {//查詢會員所屬特定相簿類別
		list = listalbcls;
	} else {//查詢會員所屬相簿
		list = albumSvc.getAlbumbymemno(loginmemno);
	}
	pageContext.setAttribute("loginmemno", loginmemno);
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="albcls"
	class="com.bikefunclub.albcls.model.AlbclsService" />
<jsp:useBean id="memSvc" class="com.bikefunclub.member.model.MemService" />
<div class="container body-content">
	<div class="row">
		<div class="col-md-3">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">相簿分類查詢</h3>
				</div>
				<div class="panel-body">
					<form method="post"
						action="<%=request.getContextPath()%>/AlbumServlet">
						<span>相簿分類:</span> <select name="albclsno">
							<option value="0">全部</option>
							<c:forEach var="albclsVO" items="${albcls.all}">
								<c:choose>
									<c:when test="${albclsVO.albclsno==albclsno}">
										<option value="${albclsVO.albclsno}" selected>${albclsVO.albclsname}</option>
									</c:when>
									<c:when test="${albclsVO.albclsno!=albclsno}">
										<option value="${albclsVO.albclsno}">${albclsVO.albclsname}</option>
									</c:when>
								</c:choose>
							</c:forEach>
						</select> <input class="btn btn-primary" type="submit" value="查詢">
						<input type="hidden" name="action"
							value="getOneAlbclsbymem_For_Display">
					</form>
				</div>
			</div>
		</div>
		<div class="col-md-9">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<span class="red">請修正以下錯誤:</span>
				<ul class="red">
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">個人相簿管理</h3>
				</div>
				<div class="panel-body">
					<table class="table table-hover">
						<tr>
							<th>相簿分類</th>
							<th>相簿標題</th>
							<th>相簿描述</th>
							<th>建立會員</th>
							<th>建立日期</th>
							<th>檢視相片</th>
							<th>刪除相簿</th>
						</tr>
						<%@ include file="page1.file"%>
						<c:forEach var="albumVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td><c:forEach var="albclsVO" items="${albcls.all}">
										<c:if test="${albclsVO.albclsno==albumVO.albclsno}">
						${albclsVO.albclsname}</c:if>
									</c:forEach></td>
								<td>${albumVO.albtitle}</td>
								<td>${albumVO.albdesc}</td>
								<td><c:forEach var="memVO" items="${memSvc.all}">
										<c:if test="${memVO.memno==albumVO.memno}">
						${memVO.memname}</c:if>
									</c:forEach></td>
								<td><fmt:formatDate value="${albumVO.albtime}"
										pattern="yyyy-MM-dd" /></td>
								<td>
									<form method="post"
										action="<%=request.getContextPath()%>/AlbumServlet">
										<input class="btn btn-primary" type="submit" value="檢視相片">
										<input type="hidden" name="albno" value="${albumVO.albno}">
										<input type="hidden" name="action"
											value="GET_ALBNO_TO_MYPHOTO">
									</form>
								</td>
								<td>
									<form method="post"
										action="<%=request.getContextPath()%>/AlbumServlet">
										<input class="btn btn-primary" type="submit" value="刪除相簿"> <input type="hidden"
											name="photono" value="${photoVO.photono}"> <input
											type="hidden" name="albno" value="${albumVO.albno}">
										<input type="hidden" name="action" value="GET_ALBNO_TO_Delete">
										<input type="hidden" name="requestURL"
											value="<%=request.getServletPath()%>"> <input
											type="hidden" name="whichPage" value="<%=whichPage%>">
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
					<a class="btn btn-primary"
						href='<%=request.getContextPath()%>/front/album/page_addAlbum.jsp'>新增相簿</a>
				</div>
			</div>
			<%@ include file="page2.file"%>
		</div>
	</div>
</div>
