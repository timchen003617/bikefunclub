<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.album.model.*"%>


<html>
<head>
<title>相簿資料新增 - addAlbum.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message.value}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/AlbumServlet">
<table border="0">

	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memno" size = "45"
		     value ="${param.memno}"/></td><td>${errorMsgs.memno}</td>

	</tr>
	<tr>
		<td>相簿分類編號:</td>
		<td><input type="TEXT" name="albclsno" size = "45"
		     value ="${param.albclsno}"/></td><td>${errorMsgs.albclsno}</td>
	</tr>
	<!-- 權限名稱:PERSONAL,SHAREFS,PUBLIC -->
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
			 value ="${param.albtitle}"/></td><td>${errorMsgs.albtitle}</td>
	</tr>
	<tr>
		<td>相簿描述:</td>
		<td><input type="TEXT" name="albdesc" size="45" 
			 value="${param.albdesc }"/></td>${errorMsgs.albdesc}</td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
