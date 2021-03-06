<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.rotcls.model.*"%>
<%@ page import="com.bikefunclub.rot.model.*"%>
<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();

    List<RotVO> listRots_Byrotclsno =(List)request.getAttribute("listRots_Byrotclsno");
    pageContext.setAttribute("listRots_Byrotclsno",listRots_Byrotclsno);
    Integer rotclsno =(Integer)request.getAttribute("rotclsno");
    //System.out.println("rotclsno="+rotclsno);
    pageContext.setAttribute("rotclsno",rotclsno);
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div id="backmain" class="col-md-10">
	<h1 class="page-header">來自會員編輯路線-路線分類</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">來自會員編輯路線-路線分類</h3>
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
			<%@ include file="pages/page1_listfromrotclsno.file"%>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>路線編號</th>
							<th>路線名稱</th>
							<th>路線分類</th>
							<th>發起會員</th>
							<th>路線權限</th>
							<th>刪除</th>
							<th>詳細資料</th>
						</tr>
					</thead>
					<tbody>
					<jsp:useBean id="rotclsSvc" scope="page"
								 class="com.bikefunclub.rotcls.model.RotclsService" />
						<c:forEach var="rotVO" items="${listRots_Byrotclsno}"
							begin="<%=pageIndex1%>" end="<%=pageIndex1+rowsPerPage1-1%>">
							<tr>
								<td>${rotVO.rotno}</td>
								<td>${rotVO.rotname}</td>
								<td><c:forEach var="rotclsVO" items="${rotclsSvc.all}">
								<c:if test="${rotVO.rotclsno==rotclsVO.rotclsno}">
	                       				    ${rotVO.rotclsno}【<font color=orange>${rotclsVO.rotclsname}</font>】
                    			</c:if>
								</c:forEach></td>
								<td>${rotVO.memno}</td>
								<td>${rotVO.rotauth}</td>
								<td>
									<FORM METHOD="post" ACTION="<%=contextpath%>/Rot.do">
										<input class="btn btn-warning" type="submit" value="刪除"> <input type="hidden"
											name="rotno" value="${rotVO.rotno}"> <input
											type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage1" value="<%=whichPage1%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="delete_fromrotclsno">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post" ACTION="<%=contextpath%>/Rot.do">
										<input class="btn btn-warning" type="submit" value="詳細資料"> <input
											type="hidden" name="rotno" value="${rotVO.rotno}"> <input
											type="hidden" name="requestURL" value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="whichPage1" value="<%=whichPage1%>">
										<!--送出當前是第幾頁給Controller-->
										<input type="hidden" name="action" value="back_getRot_info">
									</FORM>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<%@ include file="pages/page3_listfromrotclsno.file"%>
		</div>
	</div>
</div>