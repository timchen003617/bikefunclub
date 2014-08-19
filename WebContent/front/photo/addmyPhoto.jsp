<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.photo.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
%>
<div class="container body-content">
	<div class="col-md-12">
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<span class='red'>請修正以下錯誤:</span>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message.value}</li>
				</c:forEach>
			</ul>
		</c:if>
		<form method="post" action="<%=request.getContextPath()%>/PhotoServlet" enctype="multipart/form-data">
			<table>
				<tr>
					<td>
						<!--預覽圖片顯示區域 -->
						<c:if test="${not empty photoVO.phfile}">
						<div id="preview">
							<img id="imghead" src="#">
						</div></c:if>
						<c:if test="${empty photoVO.phfile}">
						<div id="preview">
							<img id="imghead" src="<%=request.getContextPath()%>/img/photo_default.png">
						</div></c:if><br> <!--圖片上傳區域 --> <input type="file" id="InputFile"
						name="phfile" value="${photoVO.phfilename}.${photoVO.phextname}"
						onchange="previewImage(this,'imghead')" required>
					</td>
				</tr>
			</table>
			<br> <input type="hidden" name="action" value="myinsert">
			<input type="submit" value="送出新增"> <input type="hidden"
				name="albno" value="<%=request.getParameter("albno")%>"> <input
				type="hidden" name="memno" value="${memVO.memno}"> <input
				type="hidden" name="phass" value="1">
		</form>
	</div>
</div>