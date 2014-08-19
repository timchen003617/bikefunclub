<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.album.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();
	AlbumService albumSvc = new AlbumService();
	List<AlbumVO> list = albumSvc.getAll();
	pageContext.setAttribute("list", list);
	
%>
<jsp:useBean id="albcls" class="com.bikefunclub.albcls.model.AlbclsService"/>
<div class="container body-content">
	<div class="row">
		<div class="col-md-3">
		<FORM METHOD="post" action="<%=request.getContextPath()%>/AlbclsServlet">
			<select name="albclsno">
				<c:forEach var="albclsVO" items="${albcls.all}">
					<option value="${albclsVO.albclsno}">${albclsVO.albclsname}</option>
				</c:forEach>
			</select> <input class="btn btn-warning" type="submit" value="查詢">
				      <input type="hidden" name="action" value="getOneAlbcls_For_Display">
		</FORM>
		</div>
		<div class="col-md-9">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<div class='red'>
					請修正以下錯誤:
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li>${message}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
				<%@ include file="pages/page1.file"%>
				<div class="table-responsive">
			<table class="table table-hover">
			<thead>
				<tr>
					<th>相簿編號</th>
					<th>會員編號</th>
					<th>相簿分類編號</th>
					<th>權限名稱</th>
					<th>相簿標題</th>
					<th>相簿描述</th>
					<th>建立時間</th>
					<th>檢視相簿</th>
					<th>修改</th>
					<th>刪除</th>

				</tr>
	 </thead>
	 	<tbody>
				<c:forEach var="albumVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<tr ${(albumVO.albno==param.albno)? 'style="background-color:#f5f0e9;"' : ''}>
						<!--將修改的那一筆加入對比色而已-->
						<td>${albumVO.albno}</td>
						<td>${albumVO.memno}</td>
						<td>${albumVO.albclsno}</td>
						<td>${albumVO.authname}</td>
						<td>${albumVO.albtitle}</td>
						<td>${albumVO.albdesc}</td>
						<td>${albumVO.albtime}</td>

						<td>
							<FORM METHOD="post"
								action="<%=request.getContextPath()%>/AlbumServlet">
								<input class="btn btn-warning" type="submit" value="檢視相簿"> <input type="hidden"
									name="albno" value="${albumVO.albno}"> <input
									type="hidden" name="action" value="GET_ALBNO_TO_PHOTO">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								action="<%=request.getContextPath()%>/AlbumServlet">
								<input class="btn btn-warning" type="submit" value="修改"> <input type="hidden"
									name="albno" value="${albumVO.albno}"> <input
									type="hidden" name="action" value="getOne_For_Update">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								action="<%=request.getContextPath()%>/AlbumServlet">
								<input class="btn btn-warning" type="submit" value="刪除"> <input type="hidden"
									name="albno" value="${albumVO.albno}"> <input
									type="hidden" name="action" value="delete"> <input
									type="hidden" name="requestURL"
									value="<%=request.getServletPath()%>"> <input
									type="hidden" name="whichPage" value="<%=whichPage%>">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			</div>
			<%@ include file="pages/page2.file"%>
		</div>
	</div>
</div>
