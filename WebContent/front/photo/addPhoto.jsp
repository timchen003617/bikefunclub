<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.photo.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	pageContext.setAttribute("memVO",memVO);
%>

<html>
<head>
<title>相片資料新增 - addPhoto.jsp</title>
</head>
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PhotoServlet"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>相片檔案:</td>
				<td>
					<!--預覽圖片顯示區域 -->
					<div id="preview">
						<img id="imghead" src="#">
					</div> <br> <!--圖片上傳區域 --> <input type="file" id="InputFile"
					name="phfile" value="${photoVO.phfilename}.${photoVO.phextname}"
					onchange="previewImage(this,'imghead')" required>
				</td>

			</tr>
<!-- 						<tr> -->
<!-- 				<td>相片檔案:</td> -->
<!-- 				<td> -->
<!-- 					預覽圖片顯示區域 -->
<!-- 					<div id="preview"> -->
<!-- 						<img id="imghead" src="#"> -->
<!-- 					</div> <br> 圖片上傳區域 <input type="file" id="InputFile" -->
<%-- 					name="phfile" value="${photoVO.phfilename}.${photoVO.phextname}" --%>
<!-- 					onchange="previewImage(this,'imghead')" required> -->
<!-- 				</td> -->

<!-- 			</tr> -->
<!-- 						<tr> -->
<!-- 				<td>相片檔案:</td> -->
<!-- 				<td> -->
<!-- 					預覽圖片顯示區域 -->
<!-- 					<div id="preview"> -->
<!-- 						<img id="imghead" src="#"> -->
<!-- 					</div> <br> 圖片上傳區域 <input type="file" id="InputFile" -->
<%-- 					name="phfile" value="${photoVO.phfilename}.${photoVO.phextname}" --%>
<!-- 					onchange="previewImage(this,'imghead')" required> -->
<!-- 				</td> -->

<!-- 			</tr> -->
<!-- 						<tr> -->
<!-- 				<td>相片檔案:</td> -->
<!-- 				<td> -->
<!-- 					預覽圖片顯示區域 -->
<!-- 					<div id="preview"> -->
<!-- 						<img id="imghead" src="#"> -->
<!-- 					</div> <br> 圖片上傳區域 <input type="file" id="InputFile" -->
<%-- 					name="phfile" value="${photoVO.phfilename}.${photoVO.phextname}" --%>
<!-- 					onchange="previewImage(this,'imghead')" required> -->
<!-- 				</td> -->

<!-- 			</tr> -->
<!-- 						<tr> -->
<!-- 				<td>相片檔案:</td> -->
<!-- 				<td> -->
<!-- 					預覽圖片顯示區域 -->
<!-- 					<div id="preview"> -->
<!-- 						<img id="imghead" src="#"> -->
<!-- 					</div> <br> 圖片上傳區域 <input type="file" id="InputFile" -->
<%-- 					name="phfile" value="${photoVO.phfilename}.${photoVO.phextname}" --%>
<!-- 					onchange="previewImage(this,'imghead')" required> -->
<!-- 				</td> -->

<!-- 			</tr> -->


		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增"> <input type="hidden" name="albno"
			value="<%=request.getParameter("albno")%>"> <input
			type="hidden" name="memno" value="${memVO.memno}"> <input
			type="hidden" name="phass" value="1">
	</FORM>
</body>

</html>
