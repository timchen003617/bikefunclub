<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.album.model.*"%>
<%@ page import="com.bikefunclub.albcls.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>

<%
	Integer albclsno = new Integer(request.getParameter("albclsno"));
	pageContext.setAttribute("albclsno", albclsno);
	AlbclsService albclsSvc = new AlbclsService();
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
%>
<div class="container body-content">
	<div class="col-md-12">
		<div id="popupcalendar" class="text"></div>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<span class='red'>請修正以下錯誤:</span>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message.value}</li>
				</c:forEach>
			</ul>
		</c:if>

		<form method="post"
			action="<%=request.getContextPath()%>/AlbumServlet">
			<table>
				<tr>
					<td>相簿分類名稱:</td>
					<td><%=albclsSvc.getAlbclsname(albclsno)%></td>
				</tr>
				<!-- 權限名稱:PERSONAL,SHAREFS,PUBLIC -->
				<tr>
					<td>權限:</td>
					<td><select size="1" name="authname">
							<option value="PERSONAL">個人</option>
							<option value="SHAREFS">分享好友</option>
							<option value="PUBLIC">公開</option>
					</select>
				</tr>
				<tr>
					<td>相簿標題:</td>
					<td><input type="TEXT" name="albtitle" size="45"
						value="${param.albtitle}" /></td>
					<td>${errorMsgs.albtitle}</td>
				</tr>
				<tr>
					<td>相簿描述:</td>
					<td><input type="TEXT" name="albdesc" size="45"
						value="${param.albdesc}" /></td>
					<td>${errorMsgs.albdesc}</td>
				</tr>
			</table>
			<br> <input type="hidden" name="action" value="Albcls_AddAlbum">
			<input type="hidden" name="memno" value="${memVO.memno}"> <input
				type="hidden" name="albclsno" value="${albclsno}"> <input
				type="submit" value="送出新增">
		</form>
	</div>
</div>