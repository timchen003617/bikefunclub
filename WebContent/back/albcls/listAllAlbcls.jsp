<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.albcls.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();
    AlbclsService albclsSvc = new AlbclsService();
    List<AlbclsVO> list = albclsSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div id="backmain" class="col-md-10">
	<h1 class="page-header">相簿分類管理</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">相簿分類管理</h3>
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
			
	<%@ include file="pages/page1.file" %>
	<div class="table-responsive">
<table class="table table-hover">
<thead>
	<tr>
		<th>相簿分類編號</th>
		<th>相簿分類名稱</th>
<!-- 		<th>檢視分類相簿</th> -->
		<th>修改相簿分類名稱</th>
		<th>刪除相簿分類</th>

	</tr>
 </thead>
 	<tbody>
	<c:forEach var="albclsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(albclsVO.albclsno==param.albclsno)? 'style="background-color:#f5f0e9;"' : ''}>
						<!--將修改的那一筆加入對比色而已-->
			<td>${albclsVO.albclsno}</td>
			<td>${albclsVO.albclsname}</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" action="<%=request.getContextPath()%>/AlbclsServlet"> --%>
<!-- 			     <input class="btn btn-warning" type="submit" value="檢視相簿分類"> -->
<%-- 			     <input type="hidden" name="albclsno" value="${albclsVO.albclsno}"> --%>
<!-- 			     <input type="hidden" name="action"	value="back_getOneAlbcls_For_Display"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" action="<%=request.getContextPath()%>/AlbclsServlet">
			     <input class="btn btn-warning" type="submit" value="修改">
			     <input type="hidden" name="albclsno" value="${albclsVO.albclsno}">
			     <input type="hidden" name="action"	value="back_getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" action="<%=request.getContextPath()%>/AlbclsServlet">
			    <input class="btn btn-warning" type="submit" value="刪除">
			    <input type="hidden" name="albclsno" value="${albclsVO.albclsno}">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath() %>">
			    <input type="hidden" name="whichPage" value="<%=whichPage%>">
			    <input type="hidden" name="action"value="back_delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
<%@ include file="pages/page2.file" %>
</div>
</div>
</div>