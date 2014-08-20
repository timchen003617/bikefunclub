<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
 	String path = request.getContextPath();
%>
<!-- EL取javaBean 也就是VO寫法 -->
<jsp:useBean id="authSvc" scope="page"
	class="com.bikefunclub.auth.model.AuthService" />

<div id="backsidebar" class="col-md-2">
	<div id="accordion">
		<c:forEach var="authVO1" items="${authSvc.all}">
			<%--${authSvc.all} = authSvc.getAll() --%>
			<c:if test="${authVO1.authlevel=='1'}">
				<h3>${authVO1.authname}</h3>
				<div>
					<c:forEach var="authVO2" items="${authSvc.all}">
						<c:if test="${authVO1.authno==authVO2.belongauthno&&authVO2.authlevel=='2'}">
							<ul>
								<li><a href="<%=path%>/${authVO2.authurl}">${authVO2.authname}</a></li>
							</ul>
						</c:if>
					</c:forEach>
				</div>
			</c:if>
		</c:forEach>
	</div>
</div>