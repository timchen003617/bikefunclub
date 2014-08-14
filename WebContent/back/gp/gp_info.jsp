<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.gp.model.*"%>
<%@ page import="com.bikefunclub.gpcls.model.*"%>
<%
	String path = request.getContextPath();
	GpVO gpVO = (GpVO) request.getAttribute("gpVO");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div id="backmain" class="col-md-10">
	<h1 class="page-header">揪團資料</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">揪團資料</h3>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<td class="col-md-1"><b>揪團編號</b></td>
							<td class="col-md-9">${gpVO.gpno}</td>
						</tr>					
					</thead>
					<tbody>
						<tr>
							<th>發起會員編號</th>
							<td>${gpVO.memno}</td>
						</tr>

						<tr>
											<jsp:useBean id="gpclsSvc" scope="page"
								 class="com.bikefunclub.gpcls.model.GpclsService" />
							<th>揪團分類編號</th>
							<td><c:forEach var="gpclsVO" items="${gpclsSvc.all}">
								<c:if test="${gpVO.gpclsno==gpclsVO.gpclsno}">
	                       				    ${gpVO.gpclsno}【<font color=orange>${gpclsVO.gpclsname}</font>】
                    			</c:if>
								</c:forEach></td>
						</tr>

						<tr>
							<th>揪團標題</th>
							<td>${gpVO.gptitle}</td>
						</tr>

						<tr>
							<th>揪團描述</th>
							<td style="word-wrap: break-word; word-break: break-all;">${gpVO.gpdesc}</td>
						</tr>

						<tr>
							<th>揪團注意事項</th>
							<td style="word-wrap: break-word; word-break: break-all;">${gpVO.gpnote}</td>
						</tr>

						<tr>
							<th>揪團發起時間</th>
							<td><fmt:formatDate value="${gpVO.gpbuilddate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>

						<tr>
							<th>揪團報名開始日期</th>
							<td><fmt:formatDate value="${gpVO.joinstartdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>

						<tr>
							<th>揪團報名結束日期</th>
							<td><fmt:formatDate value="${gpVO.joinenddate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>

						<tr>
							<th>揪團開始日期</th>
							<td><fmt:formatDate value="${gpVO.gpstartdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>

						<tr>
							<th>揪團結束日期</th>
							<td><fmt:formatDate value="${gpVO.gpenddate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>

						<tr>
							<th>揪團最大人數</th>
							<td>${gpVO.gpmaxnum}</td>
						</tr>

						<tr>
							<th>揪團權限</th>
							<td>${gpVO.gpauth}</td>
						</tr>
						<tr>
							<th>路線</th>
							<td>
								<FORM METHOD="post"
									ACTION="<%=path%>/Rot.do">
									<input class="btn btn-warning" type="submit" value="查看路線"> 
									<input type="hidden" name="action" value="back_getRot_info">
									<input type="hidden"
										name="rotno" value="${gpVO.rotno}">
								</FORM>
							</td>
						</tr>
<%-- 						<tr>
							<th>參加會員</th>
							<td>
								<FORM METHOD="post"
									ACTION="<%=path%>/Gp.do">
									<input type="submit" value="查看參加會員"> <input type="hidden"
										name="gpno" value="${gpVO.gpno}">
									<input type="hidden" name="action" value="get_memgp">							
								</FORM>
							</td>
						</tr> --%>
<!-- 						<tr> -->
<!-- 							<th>揪團相片</th> -->
<!-- 							<td> -->
<!-- 								<FORM METHOD="post" -->
<%-- 									ACTION="<%=path%>/Gp.do"> --%>
<!-- 									<input type="submit" value="查看揪團相片"> <input type="hidden" -->
<%-- 										name="gpno" value="${gpVO.gpno}"> --%>
<!-- 									<input type="hidden" name="action" value="get_photogp"> -->
<!-- 								</FORM> -->
<!-- 							</td> -->
<!-- 						</tr>						 -->
					</tbody>
				</table>

			</div>
		</div>
	</div>
</div>