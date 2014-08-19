<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.albcls.model.*"%>
<%
	AlbclsVO albclsVO = (AlbclsVO) request.getAttribute("albclsVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<div id="backmain" class="col-md-10">
	<h1 class="page-header">修改揪團分類</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">修改揪團分類</h3>
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

			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/AlbclsServlet">
				<div class="table-responsive">
					<table class="table table-bordered">
						<tr>
							<th>相簿分類編號:</th>
							<th><%=albclsVO.getAlbclsno()%></th>
						</tr>
						<tr>
							<td>相簿分類名稱:</td>
							<td><input type="TEXT" name="albclsname" size="45"
								value="<%=albclsVO.getAlbclsname()%>" /></td>
						</tr>
					</table>
				</div>

				<input type="hidden" name="action" value="update"> <input
					type="hidden" name="albclsno" value="<%=albclsVO.getAlbclsno()%>">
				<input type="submit" value="送出修改">
			</FORM>
		</div>
	</div>
</div>