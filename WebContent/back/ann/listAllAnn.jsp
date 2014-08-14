<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.Ann.model.*"%>
<%@ page import="com.bikefunclub.emp.model.*"%>
<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();
	AnnService annSvc = new AnnService();
	List<AnnVO> list = annSvc.getAll();
	pageContext.setAttribute("list", list);
	
	EmpService empSvc = new EmpService();
	List<EmpVO> list2 =  empSvc.getAll();
	pageContext.setAttribute("list2", list2);
%>
<div id="backmain" class="col-md-10">
	<h1 class="page-header">最新公告管理</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">公告管理</h3>
		</div>
		<div class="panel-body">
			<%@ include file="pages/page1.file"%>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>公告標題</th>
							<th>公告內容</th>
							<th>公告日期</th>
							<th>公告圖片</th>
							<th>管理員</th>
							<th>修改</th>
							<th>刪除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="annVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr
								${(annVO.annno==param.annno) ? 'style="background-color:#f5f0e9;"' : ''}>
								<td>${annVO.anntitle}</td>
								<td>${annVO.anncontent}</td>
								<td><fmt:formatDate value="${annVO.anndate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>

								<td><c:if test="${annVO.annfile!=null}">
										<img id="ann"
											src="<%=contextpath%>/AnnreadimgServlet?annno=${annVO.annno}">
									</c:if></td>
								<td>
								<c:forEach var="EmpVO" items="${list2}">
								<c:if test="${annVO.empno==EmpVO.empno}">
								${EmpVO.empname}
								</c:if>
									</c:forEach></td>
								<td>
									<form method="post" action="<%=contextpath%>/AnnServlet">
										<input class="btn btn-warning" type="submit" value="修改"><input type="hidden"
											name="annno" value="${annVO.annno}"> <input
											type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage" value="<%=whichPage%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="getOne_For_Update">
									</form>
								</td>
								<td>
									<form method="post" action="<%=contextpath%>/AnnServlet">
										<input class="btn btn-warning" type="submit" value="刪除"> <input type="hidden"
											name="annno" value="${annVO.annno}"><input
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


