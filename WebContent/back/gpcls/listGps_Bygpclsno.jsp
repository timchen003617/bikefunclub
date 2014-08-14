<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.gpcls.model.*"%>
<%@ page import="com.bikefunclub.gp.model.*"%>
<jsp:useBean id="listGps_Bygpclsno" scope="request"
	type="java.util.List" />
<jsp:useBean id="gpclsSvc" scope="page"
	class="com.bikefunclub.gpcls.model.GpclsService" />
<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();
    List<GpVO> listGps_Bygpclsno1 =(List)request.getAttribute("listGps_Bygpclsno");
    pageContext.setAttribute("listGps_Bygpclsno",listGps_Bygpclsno1);
    Integer gpclsno =(Integer)request.getAttribute("gpclsno");
    //System.out.println("gpclsno="+gpclsno);
    pageContext.setAttribute("gpclsno",gpclsno); 
%>
<div id="backsidebar" class="col-md-2"></div>
<div id="backmain" class="col-md-10">
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">修改揪團分類</h3>
		</div>
		<div class="panel-body">
			<%@ include file="pages/page1_listfromgpclsno.file"%>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>揪團編號</th>
							<th>揪團名稱</th>
							<th>揪團分類編號(名稱)</th>
							<th>修改分類</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="gpVO" items="${listGps_Bygpclsno}" begin="<%=pageIndex1%>"
							end="<%=pageIndex1+rowsPerPage1-1%>">
							<tr
								${(gpVO.gpno==param.gpno) ? 'style="background-color:#f5f0e9;"' : ''}>
								<td>${gpVO.gpno}</td>
								<td>${gpVO.gptitle}</td>
								<td><c:forEach var="gpclsVO" items="${gpclsSvc.all}">
										<c:if test="${gpVO.gpclsno==gpclsVO.gpclsno}">
	                    ${gpVO.gpclsno}【<font color=orange>${gpclsVO.gpclsname}</font>】
                    </c:if>
									</c:forEach></td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=contextpath%>/Gpcls.do">
										<input class="btn btn-warning" type="submit" value="修改分類"> <input
											type="hidden" name="gpno" value="${gpVO.gpno}"> 
										<input type="hidden" name="whichPage1" value="<%=whichPage1%>">
										<input type="hidden" name="requestURL"
											value="<%=servletpath%>">
										<!--送出本網頁的路徑給Controller-->
										<input type="hidden" name="action"
											value="getOne_For_Updategpcls">
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