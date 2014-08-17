<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.blog.model.*"%>
<%@ page import="com.bikefunclub.blogcls.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%
	String path = request.getContextPath();
	BlogVO blogVO = (BlogVO) request.getAttribute("blogVO");

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
<div class="container body-content">
	<div class="row">
	 <div class="text-right">
					<input class="btn btn-warning" type="button" value="上一頁"
						onClick="history.back();return true;" name="button">
				    </div>
	     
	     
		<div id="backmain" class="col-md-10">
			<h1 class="page-header">網誌詳細資訊</h1>
			<div class="panel panel-warning">
			    
			     
				<div class="panel-heading">
					<h3 class="panel-title">網誌資料</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<td class="col-md-3"><b>網誌編號</b></td>
									<td class="col-md-9">${blogVO.blogno}</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<!--會員編號 -->
									<th>發起會員編號</th>
									<td><c:forEach var="MemVO" items="${list2}">
											<c:if test="${blogVO.memno==MemVO.memno}">
										    ${MemVO.memname}
									  </c:if>
										</c:forEach></td>
								</tr>

								<tr>

									<th>網誌分類</th>
									<td><c:forEach var="blogclsVO" items="${list3}">
											<c:if test="${blogVO.blogclsno==blogclsVO.blogclsno}">
									 ${blogclsVO.blogclsname}
							   </c:if>
										</c:forEach></td>
								</tr>

								<tr>
									<th>網誌標題</th>
									<td>${blogVO.bgtitle}</td>
								</tr>

								<tr>
									<th>網誌內容</th>
									<td>${blogVO.bgtext}</td>
								</tr>
								<tr>
									<th>網誌更改時間</th>
									<td><fmt:formatDate value="${blogVO.bgtime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>