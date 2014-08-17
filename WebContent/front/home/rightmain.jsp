<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.gp.model.*"%>
<%@ page import="com.bikefunclub.gpcls.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%@ page import="com.bikefunclub.rot.model.*"%>
<%@ page import="com.bikefunclub.rotcls.model.*"%>
<%@ page import="com.bikefunclub.blog.model.*"%>
<%@ page import="com.bikefunclub.blogcls.model.*"%>
<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();
	
	GpService gpSvc = new GpService();
	List<GpVO> gplist = gpSvc.getAll();
	pageContext.setAttribute("gplist", gplist);
	
	RotService rotSvc = new RotService();
	List<RotVO> rotlist = rotSvc.getAll();
	pageContext.setAttribute("rotlist", rotlist);
	
	BlogService blogSvc = new BlogService();
	List<BlogVO> bloglist = blogSvc.getAll();
	pageContext.setAttribute("bloglist", bloglist);
%>
<script type="text/javascript">
	$(document).ready(
			function() {

				$("#gp").find("tr").click(
						function() {
							$("#formhiddengp").find("#gpno").attr(
									"value",
									$(this).find("input[type='hidden']").attr(
											"value"));
							$("#formhiddengp").submit();
						});

				$("#rot").find("tr").click(
						function() {
							$("#formhiddenrot").find("#rotno").attr(
									"value",
									$(this).find("input[type='hidden']").attr(
											"value"));
							$("#formhiddenrot").submit();
						});

				$("#blog").find("tr").click(
						function() {
							$("#formhiddenblog").find("#blogno").attr(
									"value",
									$(this).find("input[type='hidden']").attr(
											"value"));
							$("#formhiddenblog").submit();
						});

			});
</script>
<script type="text/javascript">
$(function(){
    $('td').countdown(function(s, d){
        var items = $(this).find('span');
        items.eq(4).text(d.total);
        items.eq(3).text(d.second);
        items.eq(2).text(d.minute);
        items.eq(1).text(d.hour);
        items.eq(0).text(d.day);
    });
});
</script>
<jsp:useBean id="nowDate" class="java.util.Date"/>
<fmt:formatDate var="now" value="${nowDate}" pattern="yyyy-MM-dd HH:mm:ss" /> 

<div class="col-md-8">
	<div id="colmd4-1">
		<!-- Nav tabs -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">最新揪團</h3>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>揪團分類</th>
							<th>揪團名稱</th>
							<th>人數限制</th>
							<th>報名狀態</th>
							<th>倒數計時</th>
						</tr>
					</thead>
					<tbody id="gp">
						<c:forEach var="gpVO" items="${gplist}" begin="0" end="5">
						<c:if test="${gpVO.joinenddate>nowDate}">
							<tr style="cursor: pointer">
								<jsp:useBean id="gpclsSvc" scope="page"
									class="com.bikefunclub.gpcls.model.GpclsService" />
								<td><c:forEach var="gpclsVO" items="${gpclsSvc.all}">
										<c:if test="${gpVO.gpclsno==gpclsVO.gpclsno}">
	                       			【${gpclsVO.gpclsname}】
											</c:if>
									</c:forEach></td>
								<td>${gpVO.gptitle}<input type="hidden" name="gpno"
									value="${gpVO.gpno}"></td>
								<td>${gpVO.gpmaxnum}</td>
								<td><c:choose>
										<c:when test="${rs.rowCount>=gpVO.gpmaxnum}">已滿</c:when>
										<c:otherwise>未滿</c:otherwise>
									</c:choose></td>
								<fmt:parseDate var="endDate" value="${gpVO.joinenddate}" pattern="yyyy-MM-dd HH:mm:ss"/>						
	
								<td class="red" data-seconds="${(endDate.time-nowDate.time)/1000}">
								<span></span>天   <span> </span>時 <span>:</span>分<span>:</span>秒</td>
							</tr>
							</c:if>
						</c:forEach>

					</tbody>
				</table>
				<form id="formhiddengp" method="post" action="<%=contextpath %>/Gp.do">
					<input type="hidden" name="action" value="gpdetail"> <input
						type="hidden" name="gpno" id="gpno">
				</form>
			</div>
		</div>
	</div>

	<div id="colmd4-2">
		<!-- Nav tabs -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">最新路線</h3>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>路線分類</th>
							<th>路線名稱</th>
						</tr>
					</thead>
					<tbody id="rot">
						<c:forEach var="rotVO" items="${rotlist}" begin="0" end="5">
							<tr style="cursor: pointer">
								<jsp:useBean id="rotclsSvc" scope="page"
									class="com.bikefunclub.rotcls.model.RotclsService" />
								<td><c:forEach var="rotclsVO" items="${rotclsSvc.all}">
										<c:if test="${rotVO.rotclsno==rotclsVO.rotclsno}">
	                       			【${rotclsVO.rotclsname}】
											</c:if>
									</c:forEach></td>
								<td>${rotVO.rotname}<input type="hidden" name="rotno"
									value="${rotVO.rotno}"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<form id="formhiddenrot" method="post" action="<%= contextpath%>/Rot.do">
					<input type="hidden" name="action" value="getRot_info"> <input
						type="hidden" name="rotno" id="rotno">
				</form>
			</div>
		</div>
	</div>

	<div id="colmd4-3">
		<!-- Nav tabs -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">最新網誌</h3>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>網誌分類</th>
							<th>網誌標題</th>
						</tr>
					</thead>
					<tbody id="blog">
						<c:forEach var="blogVO" items="${bloglist}" begin="0" end="5">
							<tr style="cursor: pointer">
								<jsp:useBean id="blogclsSvc" scope="page"
									class="com.bikefunclub.blogcls.model.BlogclsService" />
								<td><c:forEach var="blogclsVO" items="${blogclsSvc.all}">
										<c:if test="${blogVO.blogclsno==blogclsVO.blogclsno}">
	                       			【${blogclsVO.blogclsname}】
											</c:if>
									</c:forEach></td>
								<td>${blogVO.bgtitle}<input type="hidden" name="blogno"
									value="${blogVO.blogno}"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<form id="formhiddenblog" method="post"
					action="<%= contextpath%>/BlogServlet">
					<input type="hidden" name="action" value="getBlog_info"> <input
						type="hidden" name="blogno" id="blogno">
				</form>
			</div>
		</div>
	</div>
</div>