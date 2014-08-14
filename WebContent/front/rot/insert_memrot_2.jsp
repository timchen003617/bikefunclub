<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%
	String path = request.getContextPath();
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	Integer memno = memVO.getMemno();
	pageContext.setAttribute("memno", memno);
%>
<!DOCTYPE html>
<html>
<head>
<title>路線資料</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>
	<form class="form-register" action="<%=path%>/Rot.do" method="post">
		<fieldset>
			<!-- 			<legend>編輯路線資料</legend> -->
			<p class="red">*為必填欄位</p>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<div>
					<p class="red">請修正以下錯誤:</p>
					<ul class="red">
						<c:forEach var="message" items="${errorMsgs}">
							<li>${message}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<div>
				<label for="rotname" class="label"><span class="red">*路線名稱</span></label>
				<input type="text" name="rotname" id="rotname" placeholder="路線名稱" />
			</div>
			<div>
				<label for="rotclsno" class="label"><span class="red">*路線分類</span></label>
				<jsp:useBean id="rotclsSvc" scope="page"
					class="com.bikefunclub.rotcls.model.RotclsService" />
				<select size="1" name="rotclsno">
					<c:forEach var="rotclsVO" items="${rotclsSvc.all}">
						<option value="${rotclsVO.rotclsno}">${rotclsVO.rotclsname}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<label for="rottag" class="label">經過地區</label><input type="text"
					name="rottag" id="rottag" maxlength="50" placeholder="高雄愛河..." />
			</div>
			<div>
				<label for="rotauth" class="label"><span class="red">*分享對象</span></label>
				<select size="1" name="rotauth">
					<option value="PUBLIC">公開</option>
					<option value="PERSONAL">個人</option>
				</select>
			</div>
			<div>
				<label for="rotdesc" class="label">路線描述</label>
				<textarea name="rotdesc" id="rotdesc" rows="4" cols="50"></textarea>
			</div>
		</fieldset>
		<br>
		<div>
			<input type="hidden" name="action" value="insert_mem">
            <input type="hidden" name="rotloc" value=<%=request.getParameter("rotloc") %>>
            <input type="hidden" name="rotstart" value=<%=request.getParameter("rotstart") %>>
            <input type="hidden" name="rotend" value=<%=request.getParameter("rotend") %>>
            <input type="hidden" name="memno" value="${memno}">
			<input class="btn btn-primary btn-lg" type="submit" value="建立路線">
		</div>
	</form>
</body>
</html>