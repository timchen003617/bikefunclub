<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<div id="backmain" class="col-md-6">
	<h1 class="page-header">新增推薦路線</h1>
	<div class="panel panel-warning">
			<div class="panel-heading">
			<h3 class="panel-title">填寫路線資料</h3>
		</div>
		<div class="panel-body">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<p class="red">請修正以下錯誤:</p>
				<ul class="red">
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<br/>
			<FORM METHOD="post" ACTION="<%=path%>/Rot.do" name="form1" >
				<div>
					<b class="page-header">路線名稱:</b> <input type="TEXT"
						name="rotname" size="110"/>
				</div><br/>
				<div>
					<b class="page-header">路線分類:</b>
					<jsp:useBean id="rotclsSvc" scope="page"
						class="com.bikefunclub.rotcls.model.RotclsService" />
					<select size="1" name="rotclsno">
						<c:forEach var="rotclsVO" items="${rotclsSvc.all}">
							<option value="${rotclsVO.rotclsno}">${rotclsVO.rotclsname}
						</c:forEach>
					</select>
				</div><br/>
				<div>
					<b class="page-header">經過地區:</b> <input type="TEXT"
						name="rottag" size="110"/>
				</div><br/>
				<div>
					<b class="page-header">路線描述:</b>
					<textarea name="rotdesc" rows="10" cols="109"></textarea>
				</div>
				<br>
						<div class="text-center">
			<input type="hidden" name="action" value="insert_sg">
			<input type="hidden" name="rotauth" value="PUBLIC">
            <input type="hidden" name="rotloc" value=<%=request.getParameter("rotloc") %>>
            <input type="hidden" name="rotstart" value=<%=request.getParameter("rotstart") %>>
            <input type="hidden" name="rotend" value=<%=request.getParameter("rotend") %>>
			<input class="btn btn-warning btn-lg" type="submit" value="建立路線">
		</div>
			</FORM>
		</div>
	</div>
</div>