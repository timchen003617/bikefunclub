<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>

<%@ page import="com.bikefunclub.blogcls.model.*"%>
<%@ page import="com.bikefunclub.blog.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>

<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();

	BlogService blogSvc = new BlogService();
	List<BlogVO> list = blogSvc.getAll();
	pageContext.setAttribute("list", list);

	MemService memsvc = new MemService();
	List<MemVO> list2 = memsvc.getAll();
	pageContext.setAttribute("list2", list2);

	BlogclsService blogclssvc = new BlogclsService();
	List<BlogclsVO> list3 = blogclssvc.getAll();
	pageContext.setAttribute("list3", list3);
%>

<div id="backmain" class="col-md-10">
	<h1 class="page-header">網誌管理</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">網誌管理</h3>
		</div>
		<div class="panel-body">
			<%@ include file="pages/page1.file"%>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>網誌編號</th>
							<th>會員姓名</th>
							<th>網誌分類</th>
							<th>網誌標題</th>
							<th>網誌內容</th>
							<th>發布時間</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="blogVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr
								${(BlogVO.blogno==param.blogno) ? 'style="background-color:#f5f0e9;"' : ''}>
								<td>${blogVO.blogno}</td>
								<!--比對會員編號，顯示出會員名稱 -->
								<td>
								    <c:forEach var="MemVO" items="${list2}">
										<c:if test="${blogVO.memno==MemVO.memno}">
										   ${MemVO.memname}
									    </c:if>
									</c:forEach>
								</td>
								<!--比對網誌分類編號，顯示出網誌編號名稱 -->
								<td>
								   <c:forEach var="blogclsVO" items="${list3}">
										<c:if test="${blogVO.blogclsno==blogclsVO.blogclsno}">
										   ${blogclsVO.blogclsname}
									    </c:if>
									</c:forEach>
								</td>
								<td>${blogVO.bgtitle}</td>
								<td>${blogVO.bgtext}</td>
								<td><fmt:formatDate value="${blogVO.bgtime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                 
<!--                                  <td> -->
<%-- 									<form method="post" action="<%=contextpath%>/BlogServlet"> --%>
<!-- 										<input type="submit" value="修改"><input type="hidden" -->
<%-- 											name="blogno" value="${blogVO.blogno}"> <input --%>
<%-- 											type="hidden" name="requestURL" value="<%=servletpath%>"> --%>
<!-- 										送出本網頁的路徑給Controller -->
<%-- 										<input type="hidden" name="whichPage" value="<%=whichPage%>"> --%>
<!-- 										送出當前是第幾頁給Controller -->
<!-- 										<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 									</form> -->
<!-- 								</td> -->
<!-- 								<td> -->
<%-- 									<form method="post" action="<%=contextpath%>/BlogServlet"> --%>
<!-- 										<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 											name="blogno" value="${blogVO.blogno}"><input --%>
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








