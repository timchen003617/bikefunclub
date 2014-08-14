<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%@ page import="com.bikefunclub.gp.model.*"%>
<%@ page import="com.bikefunclub.gpcls.model.*"%>
<%@ page import="com.bikefunclub.memgp.model.*"%>
<%
	String path = request.getContextPath();
	MemVO loginmemVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("loginmemVO", loginmemVO);
	GpVO gpVO = (GpVO) request.getAttribute("gpVO");
	Integer gpno = gpVO.getGpno();

	MemgpService memgpSvc = new MemgpService();
	List<MemgpVO> memgpVO = memgpSvc.findBygpno(gpno);
	pageContext.setAttribute("memgpVO", memgpVO);
%>
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script src="<%=path%>/js/googlemap_dialog_normal.js"></script>
<jsp:useBean id="rotSvc" scope="page"
	class="com.bikefunclub.rot.model.RotService" />
<div class="container body-content">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">參加揪團</h3>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<form action="<%=path%>/Gp.do" method="post">
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<div>
								<p class="red">報名失敗:</p>
								<ul class="red">
									<c:forEach var="message" items="${errorMsgs}">
										<li>${message}</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
						<div class="text-right">
							<input class="btn btn-warning" type="button" value="上一頁"
								onClick="history.back();return true;" name="button">
						</div>
						<table class="table table-hover">
							<thead>
								<tr>
									<td class="col-md-2"><strong>揪團編號</strong></td>
									<td class="col-md-10">${gpVO.gpno}<input type="hidden"
										name="gpno" value="${gpVO.gpno}"></td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>發起會員</th>
									<jsp:useBean id="memVO" scope="page"
										class="com.bikefunclub.member.model.MemService" />
									<td><c:forEach var="memVO" items="${memVO.all}">
											<c:if test="${memVO.memno==gpVO.memno}">${memVO.memname}</c:if>
										</c:forEach></td>
								</tr>
								<tr>
									<jsp:useBean id="gpclsSvc" scope="page"
										class="com.bikefunclub.gpcls.model.GpclsService" />
									<th>揪團分類</th>
									<td><c:forEach var="gpclsVO" items="${gpclsSvc.all}">
											<c:if test="${gpVO.gpclsno==gpclsVO.gpclsno}">${gpclsVO.gpclsname}
                   			</c:if>
										</c:forEach><input type="hidden" name="gpclsno" value="${gpVO.gpclsno}"></td>
								</tr>

								<tr>
									<th>揪團標題</th>
									<td>${gpVO.gptitle}<input type="hidden" name="gptitle"
										value="${gpVO.gptitle}"></td>
								</tr>

								<tr>
									<th>揪團描述</th>
									<td style="word-wrap: break-word; word-break: break-all;">${gpVO.gpdesc}<input
										type="hidden" name="gpdesc" value="${gpVO.gpdesc}"></td>
								</tr>

								<tr>
									<th>注意事項</th>
									<td style="word-wrap: break-word; word-break: break-all;">${gpVO.gpnote}<input
										type="hidden" name="gpnote" value="${gpVO.gpnote}"></td>
								</tr>

								<tr>
									<th>發起時間</th>
									<td><fmt:formatDate value="${gpVO.gpbuilddate}"
											pattern="yyyy-MM-dd HH:mm:ss" /><input type="hidden"
										name="gpbuilddate" value="${gpVO.gpbuilddate}"></td>
								</tr>

								<tr>
									<th>報名開始日期</th>
									<td><fmt:formatDate value="${gpVO.joinstartdate}"
											pattern="yyyy-MM-dd HH:mm:ss" /> <input type="hidden"
										name="joinstartdatetime" value="${gpVO.joinstartdate}" /></td>
								</tr>

								<tr>
									<th>報名結束日期</th>
									<td><fmt:formatDate value="${gpVO.joinenddate}"
											pattern="yyyy-MM-dd HH:mm:ss" /><input type="hidden"
										name="joinenddatetime" value="${gpVO.joinenddate}" /></td>
								</tr>

								<tr>
									<th>活動開始日期</th>
									<td><fmt:formatDate value="${gpVO.gpstartdate}"
											pattern="yyyy-MM-dd HH:mm:ss" /><input type="hidden"
										name="gpstartdatetime" value="${gpVO.gpstartdate}" /></td>
								</tr>
								<tr>
									<th>人數限制</th>
									<td>${gpVO.gpmaxnum}<input type="hidden" name="gpmaxnum"
										value="${gpVO.gpmaxnum}" /></td>
								</tr>
								<tr>
									<th>目前報名人數</th>
									<td><c:set var="i" value="0"></c:set> <c:forEach
											var="memgpcount" items="${memgpVO}">
											<c:set var="i" value="${i+1}"></c:set>
										</c:forEach> <c:out value="${i}"></c:out></td>
								</tr>
								<tr>
									<th>路線</th>
									<td><c:forEach var="rotVO" items="${rotSvc.all}">
											<c:if test="${rotVO.rotno==gpVO.rotno}">
								${rotVO.rotname}
								</c:if>
										</c:forEach> <input type="hidden" id="rotno" name="rotno"
										value="${gpVO.rotno}" /> <input style="margin-left: 10px;"
										type="button" id="rotmap" class="btn btn-primary" value="地圖預覽" />
										<c:forEach var="rotVO" items="${rotSvc.all}">
											<c:if test="${rotVO.rotno==gpVO.rotno}">
												<div id="previewrotmap" title="${rotVO.rotname}"
													style="display: none"></div>
											</c:if>
										</c:forEach>
								</tr>
								<tr>
									<th>參加會員</th>
									<td><c:set var="i" value="0"></c:set> <c:forEach
											var="memgpmemname" items="${memgpVO}">
											<c:if test="${i>0}">
												<span>、</span>
											</c:if>								
									${memgpmemname.memname}<input type="hidden" name="attendmemno"
												value="${memgpmemname.memno}" />
											<c:set var="i" value="${i+1}"></c:set>
										</c:forEach></td>
								</tr>
								<tr>
									<th>參加揪團</th>
									<td><input type="submit" class="btn btn-primary"
										value="參加揪團" /> <!--登入人會員編號 --> <input type="hidden"
										name="loginmemno" value="${loginmemVO.memno}"> <input
										type="hidden" name="action" value="joingp" /></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>