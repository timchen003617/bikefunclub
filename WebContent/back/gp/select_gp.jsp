<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.gp.model.*"%>
<%@ page import="com.bikefunclub.gpcls.model.*"%>
<%
	String path = request.getContextPath();
%>
<div id="backmain" class="col-md-10">
	<h1 class="page-header">揪團管理</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">揪團管理</h3>
		</div>
		<div class="panel-body">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<p class="red">請修正以下錯誤:</p>
				<ul class="red">
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<ul>
<!-- 				<li> -->
<%-- 					<FORM METHOD="post" ACTION="<%=path%>/back/gp/page_listgps_fromrep.jsp"> --%>
<!-- 						<input type="submit" value="查看被檢舉的揪團"> -->
<!-- 					</FORM> -->
<!-- 				</li> -->
<!-- 				<br /> -->
				<li>
					<FORM METHOD="post" ACTION="<%=path%>/Gp.do">
						<b>輸入揪團編號 (格式為不為0的數字):</b> <input type="text" name="gpno">
						<input type="submit" value="送出"> <input type="hidden"
							name="action" value="getOne_fromgpno">
					</FORM>
				</li>
				<br />
				<li>
					<FORM METHOD="post" ACTION="<%=path%>/Gp.do">
						<b>輸入發起會員編號 (格式為不為0的數字):</b> <input type="text" name="memno">
						<input type="submit" value="送出"> <input type="hidden"
							name="action" value="listGps_frommemno">
					</FORM>
				</li>
				<br />
				<jsp:useBean id="gpclsSvc" scope="page"
					class="com.bikefunclub.gpcls.model.GpclsService" />
				<li>
					<FORM METHOD="post" ACTION="<%=path%>/Gpcls.do">
						<b>選擇揪團分類:</b> <select size="1" name="gpclsno">
							<c:forEach var="gpclsVO" items="${gpclsSvc.all}">
								<option value="${gpclsVO.gpclsno}"
								<c:if test="${not empty gpclsno}">
								 ${(gpclsno==gpclsVO.gpclsno)?'selected':'' }		
								</c:if>
								>${gpclsVO.gpclsname}
							</c:forEach>
						</select> <input type="submit" value="送出"> <input type="hidden"
							name="action" value="getGps_FromGpclsno_ForGp">
					</FORM>
				</li>
			</ul>
		</div>
	</div>
</div>