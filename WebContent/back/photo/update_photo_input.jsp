<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.photo.model.*"%>
<%
	PhotoVO photoVO = (PhotoVO) request.getAttribute("photoVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>相片資料修改 - update_photo_input.jsp</title>
</head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body>


	<h3>資料修改:</h3>
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PhotoServlet"
		enctype="multipart/form-data">
		<table border="0">
			<tr>
				<td>相片編號:</td>
				<td><%=photoVO.getPhotono()%></td>
			</tr>

			<tr>
				<td>會員編號:</td>
				<td><%=photoVO.getMemno()%></td>

			</tr>
			<tr>
				<td>相片經緯度:</td>
				<td><%=photoVO.getPhcoo()%></td>
			</tr>
			<tr>
				<td>相片關聯:</td>
				<td><input type="TEXT" name="phass" size="45"
					value="<%=photoVO.getPhass()%>" /></td>
			</tr>
			<tr>
				<td>相片上傳時間:</td>
				<td><%=photoVO.getPhup()%></td>
			</tr>
			<tr>
				<td>相片檔案:</td>
				<td>
					<!--預覽圖片顯示區域 -->
					<div id="preview">
						<img id="imghead" src="#">
					</div>
					<br> <!--圖片上傳區域 --> <input type="file" id="InputFile"
					name="phfile" value="${photoVO.phfilename}.${photoVO.phextname}"
					onchange="previewImage(this,'imghead')" required>
				</td>
			</tr>


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="photono" value="<%=photoVO.getPhotono()%>">
		<input type="hidden" name="memno" value="<%=photoVO.getMemno()%>">
		<input type="hidden" name="phup" value="<%=photoVO.getPhup()%>">
		<input type="hidden" name="requestURL"
			value="<%=request.getAttribute("requestURL")%>"> <input
			type="hidden" name="whichPage"
			value="<%=request.getAttribute("whichPage")%>"> <input
			type="submit" value="送出修改">
	</FORM>

</body>
</html>
