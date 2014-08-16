<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%
	String path = request.getContextPath();
	MemVO memVO = (MemVO) session.getAttribute("memVO");
%>
<header>
	<div class="container">
		<div class="row">
			<div class="col-md-7">
				<h1 class="logo">
					<a href="<%=path%>/front/home/index.jsp">BikeFunclub</a>
				</h1>
			</div>
			<div class="col-md-5">
				<div class="header-right">
					<ul id="menulist" class="text-right">
						<li><a href="<%=path%>/front/mem/page_myhome.jsp">我的首頁</a></li>
						<li><c:choose>
								<c:when test="${memVO!=null}">
									<strong>${memVO.memname},你好!</strong>
									<span>|</span>
									<a href="<%=path%>/LoginOut">登出</a>
								</c:when>
								<c:otherwise>
									<a href="<%=path%>/front/mem/login.jsp">登入</a>
									<span>|</span>
									<a href="<%=path%>/front/mem/memregister.jsp">註冊</a>
								</c:otherwise>
							</c:choose>
						</li>
<!-- 						<li><a href="#">訊息<span -->
<!-- 								class="glyphicon glyphicon-envelope"></span> <span class="badge">2</span></a></li> -->
					</ul>
					<div id="messagebox">
						<div id="messagebox-inner">
							<ul>
								<li><h3>first</h3></li>
								<li><h3>second</h3></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</header>