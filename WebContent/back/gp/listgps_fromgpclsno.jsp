<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.gpcls.model.*"%>
<%@ page import="com.bikefunclub.gp.model.*"%>
<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();

    List<GpVO> listGps_Bygpclsno =(List)request.getAttribute("listGps_Bygpclsno");
    pageContext.setAttribute("listGps_Bygpclsno",listGps_Bygpclsno);
    Integer gpclsno =(Integer)request.getAttribute("gpclsno");
    System.out.println("gpclsno="+gpclsno);
    pageContext.setAttribute("gpclsno",gpclsno);
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div id="backmain" class="col-md-10">
	<h1 class="page-header">來自揪團分類</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">來自揪團分類</h3>
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
			<%@ include file="pages/page1_listfromgpclsno.file"%>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>揪團編號</th>
							<th>揪團名稱</th>
							<th>揪團分類</th>
							<th>發起會員</th>
							<th>揪團發起時間</th>
							<th>揪團權限</th>
							<th>刪除</th>
							<th>詳細資料</th>
						</tr>
					</thead>
					<tbody>
					<jsp:useBean id="gpclsSvc" scope="page"
								 class="com.bikefunclub.gpcls.model.GpclsService" />
						<c:forEach var="gpVO" items="${listGps_Bygpclsno}"
							begin="<%=pageIndex1%>" end="<%=pageIndex1+rowsPerPage1-1%>">
							<tr>
								<td>${gpVO.gpno}</td>
								<td>${gpVO.gptitle}</td>
								<td><c:forEach var="gpclsVO" items="${gpclsSvc.all}">
								<c:if test="${gpVO.gpclsno==gpclsVO.gpclsno}">
	                       				    ${gpVO.gpclsno}【<font color=orange>${gpclsVO.gpclsname}</font>】
                    			</c:if>
								</c:forEach></td>
								<td>${gpVO.memno}</td>
								<td><fmt:formatDate value="${gpVO.gpbuilddate}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>${gpVO.gpauth}</td>
								<td>
									<FORM METHOD="post" ACTION="<%=contextpath%>/Gp.do">
										<input class="btn btn-warning" type="submit" value="刪除"> <input type="hidden"
											name="gpno" value="${gpVO.gpno}"> <input
											type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage1" value="<%=whichPage1%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="delete_fromgpclsno">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post" ACTION="<%=contextpath%>/Gp.do">
										<input class="btn btn-warning" type="submit" value="詳細資料"> <input
											type="hidden" name="gpno" value="${gpVO.gpno}"> <input
											type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage1" value="<%=whichPage1%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="getGp_info">
									</FORM>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<%@ include file="pages/page3_listfromgpclsno.file"%>
		</div>
	</div>
</div>