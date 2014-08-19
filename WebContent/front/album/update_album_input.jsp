<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.album.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%
	AlbumVO albumVO = (AlbumVO) request.getAttribute("albumVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
%>
<jsp:useBean id="albcls"
	class="com.bikefunclub.albcls.model.AlbclsService" />
<div class="container body-content">
	<div class="col-md-12">
		<h3>相簿資料修改:</h3>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<span class='red'>請修正以下錯誤:</span>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<form method="post"
			action="<%=request.getContextPath()%>/AlbumServlet">
			<table>
				<tr>
					<td>相簿編號:<font color=red><b>*</b></font></td>
					<td><%=albumVO.getAlbno()%></td>
				</tr>

				<tr>
					<td>相簿分類:</td>
					<td><select size="1">
					<c:forEach var="albclsVO" items="albcls">
					<option>${albclsVO.albclsname}</option>
					</c:forEach>
					</select>
					<input type="hidden" name="albclsno"
						value="<%=albumVO.getAlbclsno()%>" /></td>
				</tr>			
<!-- 					權限名稱:PERSONAL,SHAREFS,PUBLIC -->				
				<jsp:useBean id="albumSvc" scope="page"
					class="com.bikefunclub.album.model.AlbumService" />
				<tr>
					<td>權限名稱:</td>
					<td><select size="1" name="authname">
							<option value="PERSONAL">個人</option>
							<option value="SHAREFS">分享好友</option>
							<option value="PUBLIC">公開</option>
					</select>
				</tr>
				<tr>
					<td>相簿標題:</td>
					<td><input type="TEXT" name="albtitle" size="45"
						value="<%=albumVO.getAlbtitle()%>" /></td>
				</tr>
				<tr>
					<td>相簿描述:</td>
					<td><input type="TEXT" name="albdesc" size="45"
						value="<%=albumVO.getAlbdesc()%>" /></td>
				</tr>
				<tr>
					<td>建立時間:</td>
					<td><%=albumVO.getAlbtime()%></td>
				</tr>
			</table>
			<br> <input type="hidden" name="action" value="update">
			<input type="hidden" name="albno" value="<%=albumVO.getAlbno()%>">
			<input type="hidden" name="memno" value="<%=albumVO.getMemno()%>">
			<input type="hidden" name="albtime" value="<%=albumVO.getAlbtime()%>">
			<input type="submit" value="送出修改">
		</form>
	</div>
</div>
