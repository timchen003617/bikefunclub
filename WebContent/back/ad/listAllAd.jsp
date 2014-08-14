<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.ad.model.*"%>
<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();
	AdService adSvc = new AdService();
	List<AdVO> list = adSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="adVO" scope="page"
	class="com.bikefunclub.ad.model.AdService" />
<div id="backmain" class="col-md-10">
	<h1 class="page-header">廣告輪播管理</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">廣告輪播管理</h3>
		</div>
		<div class="panel-body">
			<%@ include file="pages/page1.file"%>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>廣告標題</th>
							<th>廣告內容</th>
							<th>廣告連結</th>
							<th>廣告圖片</th>
							<th>修改</th>
							<th>刪除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="adVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr
								${(adVO.adno==param.adno) ? 'style="background-color:#f5f0e9;"' : ''}>
								<td>${adVO.adname}</td>
								<td>${adVO.adesc}</td>
								<td>${adVO.adurl}</td>
								<td><img
									src="<%=contextpath%>/CarouselServlet?adno=${adVO.adno}"></td>
								<td>
									<form method="post" action="<%=contextpath%>/AdServlet">
										<input class="btn btn-warning" type="submit" value="修改"> <input type="hidden"
											name="adno" value="${adVO.adno}"> <input
											type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="getOne_For_Update">
									</form>
								</td>
								<td>
									<form method="post" action="<%=contextpath%>/AdServlet">
										<input class="btn btn-warning" type="submit" value="刪除"> <input type="hidden"
											name="adno" value="${adVO.adno}"> <input
											type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="delete">
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<%@ include file="pages/page2.file"%>
		</div>
	</div>
</div>


