<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.ad.model.*"%>
<%
	String path = request.getContextPath();
%>
<!--  	AdService adSvc = new AdService(); -->
<!--  	List<AdVO> list = adSvc.getAll(); -->
<!--  	pageContext.setAttribute("list", list); -->
<!-- EL取javaBean 也就是VO寫法 -->
<jsp:useBean id="adSvc" scope="page"
	class="com.bikefunclub.ad.model.AdService" />
<c:if test="${not empty adSvc.all}">
	<div id="carousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<%
				int i = 0;
			%>
			<c:forEach var="adVO" items="${adSvc.all}">
				<%--${adSvc.all} = adSvc.getAll() --%>
				<%
					if (i == 0) {
				%>
				<li data-target="#carousel" data-slide-to="0" class="active"></li>
				<%
					} else {
				%>
				<li data-target="#carousel" data-slide-to="<%=i%>"></li>
				<%
					}
				%>
				<%
					i++;
				%>
			</c:forEach>
		</ol>
		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<%
				int j = 0;
			%>
			<c:forEach var="adVO" items="${adSvc.all}">
				<%
					if (j == 0) {
				%>
				<div class="item active">
					<a href="${adVO.adurl}" target="_blank"> <img
						src="<%=path%>/CarouselServlet?adno=${adVO.adno}"
						alt="${adVO.adname}"></a>
					<div class="carousel-caption">
						<h1>${adVO.adname}</h1>
						<p>${adVO.adesc}</p>
					</div>
				</div>
				<%
					} else {
				%>
				<div class="item">
					<a href="${adVO.adurl}" target="_blank"><img
						src="<%=path%>/CarouselServlet?adno=${adVO.adno}"
						alt="${adVO.adname}"></a>
					<div class="carousel-caption">
						<h1>${adVO.adname}</h1>
						<p>${adVO.adesc}</p>
					</div>
				</div>
				<%
					}
				%>
				<%
					j++;
				%>

			</c:forEach>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#carousel" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span>
		</a> <a class="right carousel-control" href="#carousel" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right"></span>
		</a>
	</div>
</c:if>