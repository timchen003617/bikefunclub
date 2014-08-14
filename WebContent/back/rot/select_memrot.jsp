<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.rot.model.*"%>
<%@ page import="com.bikefunclub.rotcls.model.*"%>
<%
	String path = request.getContextPath();
%>
<div id="backmain" class="col-md-10">
	<h1 class="page-header">會員路線管理</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">會員路線管理</h3>
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
<%-- 					<FORM METHOD="post" ACTION="<%=path%>/back/rot/page_listmemrots_fromrep.jsp"> --%>
<!-- 						<input type="submit" value="查看被檢舉的路線"> -->
<!-- 					</FORM> -->
<!-- 				</li> -->
<!-- 				<br /> -->
				<li>
					<FORM METHOD="post" ACTION="<%=path%>/Rot.do">
						<b>輸入路線編號 (格式為不為0的數字):</b> <input type="text" name="rotno">
						<input type="submit" value="送出"> <input type="hidden"
							name="action" value="getOne_fromrotno">
					</FORM>
				</li>
				<br />
				<li>
					<FORM METHOD="post" ACTION="<%=path%>/Rot.do">
						<b>輸入編輯會員編號 (格式為不為0的數字):</b> <input type="text" name="memno">
						<input type="submit" value="送出"> <input type="hidden"
							name="action" value="listRots_frommemno">
					</FORM>
				</li>
				<br />
				<jsp:useBean id="rotclsSvc" scope="page"
					class="com.bikefunclub.rotcls.model.RotclsService" />
				<li>
					<FORM METHOD="post" ACTION="<%=path%>/Rot.do">
						<b>選擇路線分類:</b> <select size="1" name="rotclsno">
							<c:forEach var="rotclsVO" items="${rotclsSvc.all}">
								<option value="${rotclsVO.rotclsno}"
								<c:if test="${not empty rotclsno}">
								 ${(rotclsno==rotclsVO.rotclsno)?'selected':'' }		
								</c:if>
								>${rotclsVO.rotclsname}
							</c:forEach>
						</select> <input type="submit" value="送出"> 
						<input type="hidden" name="action" value="getRots_FromRotclsno_ForRot">
					</FORM>
				</li>
			</ul>
		</div>
	</div>
</div>