<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.album.model.*"%>
<%@ page import="com.bikefunclub.albcls.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
%>
<jsp:useBean id="albclsSvc"
	class="com.bikefunclub.albcls.model.AlbclsService" />
<div class="container body-content">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">新增相簿</h3>
			</div>
			<div class="panel-body">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<span class="red">請修正以下錯誤:</span>
					<ul class="red">
						<c:forEach var="message" items="${errorMsgs}">
							<li>${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<form method="post"
					action="<%=request.getContextPath()%>/AlbumServlet">
					<table>
						<tr>
							<td>相簿分類:</td>
							<td><select size="1" name="albclsno">
									<c:forEach var="albclsVO" items="${albclsSvc.all}">
										<option value="${albclsVO.albclsno}">${albclsVO.albclsname}</option>
									</c:forEach>
							</select></td>
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
							<td><input type="text" name="albtitle" value="${albumVO.albtitle}" /></td>
						</tr>
						<tr>
							<td>相簿描述:</td>
							<td><textarea name="albdesc" rows="10" cols="40">${albumVO.albdesc}</textarea></td>
						</tr>
					</table>
					<br> <input type="hidden" name="action" value="addAlbum">
					<input type="hidden" name="memno" value="${memVO.memno}"><input
						type="submit" value="送出新增">
				</form>
			</div>
		</div>
	</div>
</div>