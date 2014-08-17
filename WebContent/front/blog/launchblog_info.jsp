<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%@ page import="com.bikefunclub.blog.model.*"%>
<%@ page import="com.bikefunclub.blogcls.model.*"%>

<%
	String path = request.getContextPath();
	BlogVO blogVO = (BlogVO) request.getAttribute("blogVO");
	

	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);

	
%>


<jsp:useBean id="rotSvc" scope="page"
	class="com.bikefunclub.rot.model.RotService" />
<div class="container body-content">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">詳細資料</h3>
			</div>
			<div class="panel-body">
				<div class="text-right">
					<input class="btn btn-warning" type="button" value="上一頁"
						onClick="history.back();return true;" name="button">
				</div>
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<td class="col-md-3"><strong>網誌編號</strong></td>
								<td class="col-md-9">${blogVO.blogno}</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>發起會員</th>
								<td>${memVO.memname}</td>
							</tr>
							<tr>
								<jsp:useBean id="blogclsSvc" scope="page"
									class="com.bikefunclub.blogcls.model.BlogclsService" />
								<th>網誌分類</th>
								<td><c:forEach var="blogclsVO" items="${blogclsSvc.all}">
										<c:if test="${blogVO.blogclsno==blogclsVO.blogclsno}">${blogclsVO.blogclsname}
                   			</c:if>
									</c:forEach></td>
							</tr>

							<tr>
								<th>網誌標題</th>
								<td>${blogVO.bgtitle}</td>
							</tr>

							<tr>
								<th>網誌內容</th>
								<td style="word-wrap: break-word; word-break: break-all;">${blogVO.bgtext}</td>
							</tr>
							<tr>
								<th>發佈時間</th>
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