<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.emp.model.*" %>
<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();
	EmpService empSvc = new EmpService();
	List<EmpVO> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<%-- <jsp:useBean id="EmpVO" scope="page" --%>
<%-- 	class="com.bikefunclub.emp.model.EmpService" /> --%>
<div id="backmain" class="col-md-10">
	<h1 class="page-header">員工帳號管理</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">帳號管理</h3>
		</div>
		<div class="panel-body">
			<%@ include file="pages/page1.file"%>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
						    <th>員工編號</th>
							<th>員工帳號</th>
							<th>員工姓名</th>
						    <th>員工信箱</th>
							<th>員工住址</th>
							<th>員工身分證號</th>
							<th>員工申請日期</th>
							<th>員工電話</th>
							<th>修改</th>
							<th>刪除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr
								${(empVO.empno==param.empno) ? 'style="background-color:#f5f0e9;"' : ''}>
								<td>${empVO.empno}</td>
								<td>${empVO.empacc}</td>
								<td>${empVO.empname}</td>
								<td>${empVO.empemail}</td>
								<td>${empVO.empaddr}</td>
				                <td>${empVO.empid}</td>				
								<td>${empVO.emprgdate}</td>
								<td>${empVO.emptel}</td>
								
								<td>
									<form method="post" action="<%=contextpath%>/EmpServlet">
										<input class="btn btn-warning" type="submit" value="修改"><input type="hidden"
											name="empno" value="${empVO.empno}"> <input
											type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="getOne_For_Update">
									</form>
								</td>
								<td>
									<form method="post" action="<%=contextpath%>/EmpServlet">
										<input class="btn btn-warning" type="submit" value="刪除"> <input type="hidden"
											name="empno" value="${empVO.empno}"><input
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


