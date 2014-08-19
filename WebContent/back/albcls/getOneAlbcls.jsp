<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.album.model.*"%>
<%@ page import="com.bikefunclub.albcls.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();
	List<AlbumVO> list;
	AlbclsVO albclsVO;
	if(request.getAttribute("listAlbum")!=null){
	    list = (List<AlbumVO>)request.getAttribute("listAlbum");
		albclsVO = (AlbclsVO)request.getAttribute("albclsVO");
	}else{
		AlbclsService albclsSvc = new AlbclsService();

		albclsVO = (AlbclsVO)request.getAttribute("albclsVO");	
	    list = (List<AlbumVO>)albclsSvc.findByAlbum(albclsVO.getAlbclsno());
		
		}		
	pageContext.setAttribute("albclsVO",albclsVO);
	pageContext.setAttribute("list",list);	
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div id="backmain" class="col-md-10">
	<h1 class="page-header">相簿管理</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">相簿管理</h3>
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
	<%@ include file="pages/getOneAlbcls_page1.file" %>
	<div class="table-responsive">
<table class="table table-hover">
<thead>
	<tr>
		<th>相簿編號</th>
		<th>會員編號</th>
		<th>相簿分類編號</th>
		<th>權限名稱</th>
		<th>相簿標題</th>
		<th>相簿描述</th>
		<th>建立時間</th>
		<th>檢視相簿</th>
		<th>刪除相簿</th>

	</tr>
	</thead>
 	<tbody>
	<c:forEach var="albumVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(albumVO.albno==param.albno)? 'style="background-color:#f5f0e9;"' : ''}>
						<!--將修改的那一筆加入對比色而已-->
			<td>${albumVO.albno}</td>
			<td>${albumVO.memno}</td>
			<td>${albumVO.albclsno}</td>
			<td>${albumVO.authname}</td>
			<td>${albumVO.albtitle}</td>
			<td>${albumVO.albdesc}</td>
			<td>${albumVO.albtime}</td>

			<td>
			  <FORM METHOD="post" action="<%=request.getContextPath()%>/AlbumServlet">
			     <input class="btn btn-warning" type="submit" value="檢視相簿">
			     <input type="hidden" name="albno" value="${albumVO.albno}">
			     <input type="hidden" name="action"	value="GET_ALBNO_TO_PHOTO"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" action="<%=request.getContextPath()%>/AlbumServlet"> --%>
<!-- 			     <input class="btn btn-warning" type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="albno" value="${albumVO.albno}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" action="<%=request.getContextPath()%>/AlbumServlet">
			    <input class="btn btn-warning" type="submit" value="刪除">
			    <input type="hidden" name="albno" value="${albumVO.albno}">
			    <input type="hidden" name="albclsno" value="${albclsVO.albclsno}">
			    <input type="hidden" name="action" value="back_delete">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath() %>">
			    <input type="hidden" name="whichPage" value="<%=whichPage %>"></FORM>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
<%@ include file="pages/getOneAlbcls_page2.file" %>
</div>
</div>
</div>
