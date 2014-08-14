<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.gpcls.model.*"%>
<%@ page import="com.bikefunclub.gp.model.*"%>
<%@ page import="com.bikefunclub.memgp.model.*"%>
<%
	String servletpath = request.getServletPath();
	String path = request.getContextPath();
	GpService gpSvc = new GpService();
	List<GpVO> list = gpSvc.getAll();
	pageContext.setAttribute("list", list);

	MemgpService memgpSvc = new MemgpService();
%>
<sql:setDataSource dataSource="jdbc/YA801G2" var="memgpcount"/>

<div class="container body-content">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<span class="glyphicon glyphicon-search"></span>揪團列表
				</h3>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
				<%@ include file="pages/page1.file"%>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>揪團分類</th>
								<th>揪團名稱</th>
								<th>發起會員</th>
								<th>報名時間</th>
								<th>人數限制</th>
								<th>報名人數</th>
								<th>報名狀態</th>
								<th>詳細資料</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="gpVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
 								<sql:query var="rs" dataSource="${memgpcount}"><%--揪團參加人數 --%>
     SELECT * FROM Memgp inner join member on member.memno = Memgp.memno where gpno=${gpVO.gpno}</sql:query>
								<tr>
									<jsp:useBean id="gpclsSvc" scope="page"
										class="com.bikefunclub.gpcls.model.GpclsService" />
									<td><c:forEach var="gpclsVO" items="${gpclsSvc.all}">
											<c:if test="${gpVO.gpclsno==gpclsVO.gpclsno}">
	                       			【${gpclsVO.gpclsname}】
	                       			<td>${gpVO.gptitle}</td>
											</c:if>
										</c:forEach></td>
									<jsp:useBean id="memSvc" scope="page"
										class="com.bikefunclub.member.model.MemService" />

									<td><c:forEach var="memVO" items="${memSvc.all}">
											<c:if test="${gpVO.memno==memVO.memno}">
										${memVO.memname}
										</c:if>
										</c:forEach></td>
									<td><fmt:formatDate value="${gpVO.joinstartdate}"
											pattern="yyyy-MM-dd HH:mm:ss" />-<br> <fmt:formatDate
											value="${gpVO.joinenddate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td>${gpVO.gpmaxnum}</td>
									<!--報名人數 -->
 									<td>${rs.rowCount}</td><%--查詢結果的資料筆數 --%>
									<td><c:choose><c:when test="${rs.rowCount>=gpVO.gpmaxnum}">已滿</c:when>
										<c:otherwise>未滿</c:otherwise></c:choose>
									</td>
									<td>
										<form method="post" action="<%=path%>/Gp.do">
											<input class="btn btn-primary" type="submit" value="我想報名"> <input
												type="hidden" name="gpno" value="${gpVO.gpno}"> <input
												type="hidden" name="requestURL" value="<%=servletpath%>">
											<!--送出本網頁的路徑給Controller-->
											<input type="hidden" name="whichPage" value="<%=whichPage%>">
											<!--送出當前是第幾頁給Controller-->
											<input type="hidden" name="action" value="gpdetail">
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<%@ include file="pages/page3.file"%>
			</div>
		</div>
	</div>
</div>