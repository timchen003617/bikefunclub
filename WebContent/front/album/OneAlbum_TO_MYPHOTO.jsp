<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.photo.model.*"%>
<%@ page import="com.bikefunclub.album.model.*"%>
<%@ page import="com.bikefunclub.gpalbum.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%-- 此頁採用 JSTL 與 EL 取值 --%>

<%
	List<PhotoVO> list = (List<PhotoVO>) request.getAttribute("listPohto");
	MemVO memVO = (MemVO) session.getAttribute("memVO");
%>
<script>
	$(document).ready(function() {
		$('.fancybox').each(function(){
		    $(this).fancybox({
		             'autoScale' : false,
		             'href' : $(this).attr('id'),
		             'type':'iframe',
		             'padding' : 0,
		             'closeClick'  : false,
		             //some other callbacks etc
		    });
		});
	});
</script>
<div class="container body-content">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">個人相片管理</h3>
				<div class="text-right">
					<a class="btn btn-default"
						href="<%=request.getContextPath()%>/front/album/page_myalbum.jsp">回相簿</a>
				</div>
			</div>
			<div class="panel-body">
				<div class="col-md-12">
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<span class="red">請修正以下錯誤:</span>
						<ul class="red">
							<c:forEach var="message" items="${errorMsgs}">
								<li>${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<div class="pull-right">
						<form method="post"
							action="<%=request.getContextPath()%>/front/photo/page_addmyPhoto.jsp">
							<input class="btn btn-primary" type="submit" value="新增相片">
							<input type="hidden" name="albno" value="${albumVO.albno}">
						</form>
					</div>
					<table class="table table-hover">
						<tr>
							<th>相片上傳時間</th>
							<th>相片檔案</th>
							<th>刪除相片</th>
						</tr>
						<%@ include file="page1A.file"%>
						<c:forEach var="photoVO" items="${listPohto}"
							begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td><fmt:formatDate value="${photoVO.phup}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><a id="<%=request.getContextPath()%>/PhotoPreViewServlet?photono=${photoVO.photono}" class="fancybox" data-fancybox-group="gallery"
									href="" title="${photoVO.phfilename}"><img
										src="<%=request.getContextPath()%>/PhotoPreViewServlet?photono=${photoVO.photono}" alt=""
										width='100' height='100'></a></td>
								<td>
									<form method="post"
										action="<%=request.getContextPath()%>/AlbumServlet">
										<input class="btn btn-primary btn-lg" type="submit" value="刪除">
										<input type="hidden" name="photono" value="${photoVO.photono}">
										<input type="hidden" name="albno" value="${albumVO.albno}">
										<input type="hidden" name="action" value="GET_ALBNO_TO_Delete"> <input
											type="hidden" name="requestURL"
											value="<%=request.getServletPath()%>"> <input
											type="hidden" name="whichPage" value="<%=whichPage%>">
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
					<%@ include file="page2A.file"%>
				</div>
			</div>
		</div>
	</div>
</div>