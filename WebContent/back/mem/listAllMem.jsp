<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>

<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();
	String path = request.getContextPath();
	MemService memSvc = new MemService();
	List<MemVO> list = memSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="memVO" scope="page"
	class="com.bikefunclub.member.model.MemService" />
<div id="backmain" class="col-md-10">
	<h1 class="page-header">會員資料管理</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">會員資料管理</h3>
		</div>
		<div class="panel-body">
			<%@ include file="pages/page1.file"%>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>編號</th>
							<th>帳號</th>
							<th>密碼</th>
							<th>姓名</th>
							<th>身分證</th>
							<th>生日</th>
							<th>綽號</th>
							<th>照片</th>
							<th>信箱</th>
							<th>性別</th>
							<th>郵遞區號</th>
							<th>地址</th>
							<th>手機號碼</th>
							<th>註冊日期</th>
							<!-- 							<th>修改</th> -->
							<!-- 							<th>刪除</th> -->
						</tr>
					</thead>
					<tbody>
						<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr
								${(memVO.memno==param.memno) ? 'style="background-color:#f5f0e9;"' : ''}>

								<td>${memVO.memno}</td>
								<td>${memVO.memacc}</td>
								<td>${memVO.mempw}</td>
								<td>${memVO.memname}</td>
								<td>${memVO.memid}</td>
								<td>${memVO.membirth}</td>
								<td>${memVO.memnickname}</td>
								<td><c:if test="${memVO.memno==memVO.memno}">
										<c:choose>
											<c:when test="${memVO.memfile==null}">
												<a href="#"><img id="mem" class="img-thumbnail"
													src="<%=path%>/img/photo.jpg"></a>
											</c:when>
											<c:otherwise>
												<a href="#"><img id="mem" class="img-thumbnail"
													src="<%=path%>/MemreadimgServlet?memno=${memVO.memno}"></a>
											</c:otherwise>
										</c:choose>
									</c:if></td>
								<td>${memVO.mememail}</td>
								<td><c:if test="${memVO.memsex==memVO.memsex}">
										<c:choose>
											<c:when test="${memVO.memsex=='M'}">
								        		男								           
								           </c:when>
											<c:when test="${memVO.memsex=='F'}">
								        		女
								           </c:when>
										</c:choose>
									</c:if></td>
								<td>${memVO.memzip}</td>
								<td>${memVO.memaddr}</td>
								<td>${memVO.memtelm}</td>
								<td><fmt:formatDate value="${memVO.memrgdate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<!-- 								<td> -->
								<%-- 									<form method="post" action="<%=contextpath%>/MemServlet"> --%>
								<!-- 										<input type="submit" value="修改"><input type="hidden" -->
								<%-- 											name="memno" value="${memVO.memno}"> <input --%>
								<%-- 											type="hidden" name="requestURL" value="<%=servletpath%>"> --%>
								<!-- 										送出本網頁的路徑給Controller -->
								<%-- 										<input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
								<!-- 										送出當前是第幾頁給Controller -->
								<!-- 										<input type="hidden" name="action" value="getOne_For_Update"> -->
								<!-- 									</form> -->
								<!-- 								</td> -->
								<!-- 								<td> -->
								<%-- 									<form method="post" action="<%=contextpath%>/MemServlet"> --%>
								<!-- 										<input type="submit" value="刪除"> <input type="hidden" -->
								<%-- 											name="memno" value="${memVO.memno}"><input --%>
								<%-- 											type="hidden" name="requestURL" value="<%=servletpath%>"> --%>
								<!-- 										送出本網頁的路徑給Controller -->
								<%-- 										<input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
								<!-- 										送出當前是第幾頁給Controller -->
								<!-- 										<input type="hidden" name="action" value="delete"> -->
								<!-- 									</form> -->
								<!-- 								</td> -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<%@ include file="pages/page2.file"%>
		</div>
	</div>
</div>


