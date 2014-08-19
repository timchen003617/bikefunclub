<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%@ page import="com.bikefunclub.gp.model.*"%>
<%@ page import="com.bikefunclub.gpcls.model.*"%>
<%@ page import="com.bikefunclub.memgp.model.*"%>
<%@ page import="com.bikefunclub.gpcomm.model.*"%>
<%
	String path = request.getContextPath();
	MemVO loginmemVO = (MemVO) session.getAttribute("memVO");
	if (loginmemVO == null) {
		RequestDispatcher successView = request
				.getRequestDispatcher("/front/mem/login.jsp");
		successView.include(request, response);
		return;
	}
	pageContext.setAttribute("loginmemVO", loginmemVO);
	GpVO gpVO = (GpVO) request.getAttribute("gpVO");
	Integer gpno = gpVO.getGpno();

	MemgpService memgpSvc = new MemgpService();
	List<MemgpVO> memgpVO = memgpSvc.findBygpno(gpno);
	pageContext.setAttribute("memgpVO", memgpVO);

	GpcommService gpSvc = new GpcommService();
	List<GpcommVO> commVO = gpSvc.getAll();
	pageContext.setAttribute("commVO", commVO);
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
						<a class="btn btn-primary"
						href="<%=path%>/front/gp/page_listAllgp.jsp">回瀏覽揪團</a>
					</div>
					<form action="<%=path%>/Gp.do" method="post">
						<table class="table table-hover">
							<thead>
								<tr>
									<td class="col-md-2"><strong>揪團編號</strong></td>
									<td class="col-md-8">${gpVO.gpno}<input type="hidden"
										name="gpno" value="${gpVO.gpno}"></td>
									<td></td>
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
									<td></td>
								</tr>
								<tr>
									<jsp:useBean id="gpclsSvc" scope="page"
										class="com.bikefunclub.gpcls.model.GpclsService" />
									<th>揪團分類</th>
									<td><c:forEach var="gpclsVO" items="${gpclsSvc.all}">
											<c:if test="${gpVO.gpclsno==gpclsVO.gpclsno}">${gpclsVO.gpclsname}
                   			</c:if>
										</c:forEach><input type="hidden" name="gpclsno" value="${gpVO.gpclsno}"></td>
									<td></td>
								</tr>

								<tr>
									<th>揪團標題</th>
									<td>${gpVO.gptitle}<input type="hidden" name="gptitle"
										value="${gpVO.gptitle}"></td>
									<td></td>
								</tr>

								<tr>
									<th>揪團描述</th>
									<td style="word-wrap: break-word; word-break: break-all;">${gpVO.gpdesc}<input
										type="hidden" name="gpdesc" value="${gpVO.gpdesc}"></td>
									<td></td>
								</tr>

								<tr>
									<th>注意事項</th>
									<td style="word-wrap: break-word; word-break: break-all;">${gpVO.gpnote}<input
										type="hidden" name="gpnote" value="${gpVO.gpnote}"></td>
									<td></td>
								</tr>

								<tr>
									<th>發起時間</th>
									<td><fmt:formatDate value="${gpVO.gpbuilddate}"
											pattern="yyyy-MM-dd HH:mm:ss" /><input type="hidden"
										name="gpbuilddate" value="${gpVO.gpbuilddate}"></td>
									<td></td>
								</tr>

								<tr>
									<th>報名開始日期</th>
									<td><fmt:formatDate value="${gpVO.joinstartdate}"
											pattern="yyyy-MM-dd HH:mm:ss" /> <input type="hidden"
										name="joinstartdatetime" value="${gpVO.joinstartdate}" /></td>
									<td></td>
								</tr>

								<tr>
									<th>報名結束日期</th>
									<td><fmt:formatDate value="${gpVO.joinenddate}"
											pattern="yyyy-MM-dd HH:mm:ss" /><input type="hidden"
										name="joinenddatetime" value="${gpVO.joinenddate}" /></td>
									<td></td>
								</tr>

								<tr>
									<th>活動開始日期</th>
									<td><fmt:formatDate value="${gpVO.gpstartdate}"
											pattern="yyyy-MM-dd HH:mm:ss" /><input type="hidden"
										name="gpstartdatetime" value="${gpVO.gpstartdate}" /></td>
									<td></td>
								</tr>
								<tr>
									<th>人數限制</th>
									<td>${gpVO.gpmaxnum}<input type="hidden" name="gpmaxnum"
										value="${gpVO.gpmaxnum}" /></td>
									<td></td>
								</tr>
								<tr>
									<th>目前報名人數</th>
									<td><c:set var="i" value="0"></c:set> <c:forEach
											var="memgpcount" items="${memgpVO}">
											<c:set var="i" value="${i+1}"></c:set>
										</c:forEach> <c:out value="${i}"></c:out></td>
									<td></td>
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
									<td></td>
								</tr>
								<tr>
									<th>參加會員</th>
									<td><c:set var="i" value="0"></c:set> <c:forEach
											var="memgpmemname" items="${memgpVO}">
											<c:if test="${i>0}">
												<span>、</span>
											</c:if>
											<a
												href="<%=path%>/front/home/page_mem_info.jsp?memno=${memgpmemname.memno}"
												target="_blank">${memgpmemname.memname}</a>
											<input type="hidden" name="attendmemno"
												value="${memgpmemname.memno}" />
											<c:set var="i" value="${i+1}"></c:set>
										</c:forEach></td>
									<td></td>
								</tr>
								<tr>
									<th>參加揪團</th>
									<td><input type="submit" class="btn btn-primary"
										value="參加揪團" /> <!--登入人會員編號 --> <input type="hidden"
										name="loginmemno" value="${loginmemVO.memno}"> <input
										type="hidden" name="action" value="joingp" /></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</form>
					<form action="<%=path%>/Gpcomm.do" method="post">
						<div class="text-left">
							<textarea rows="5" cols="60" name="gpcomm" /></textarea>
							<input type="submit" class="btn btn-primary" value="留言" /> <input
								type="hidden" name="commemno" value="${loginmemVO.memno}">
							<input type="hidden" name="thisgpno" value="${gpVO.gpno}">
							<input type="hidden" name="action" value="gpcomm" />
						</div>
					</form>
					<table class="table">
						<thead>
							<tr>
								<th>揪團留言</th>
								<th>留言時間</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><jsp:useBean id="memcomm" scope="page"
										class="com.bikefunclub.member.model.MemService" /> <c:forEach
										var="gpcomm" items="${commVO}">
										<c:if test="${gpcomm.gpno==gpVO.gpno}">
											<div style="height: 50px;">
												<c:forEach var="memcomm2" items="${memcomm.all}">
													<c:if test="${memcomm2.memno==gpcomm.memno}">
										${memcomm2.memname}
										</c:if>
												</c:forEach>
												:${gpcomm.gpcomm}
											</div>
										</c:if>
									</c:forEach></td>
								<td><c:forEach var="gpcomm" items="${commVO}">
										<c:if test="${gpcomm.gpno==gpVO.gpno}">
											<div style="height: 50px;">
												<fmt:formatDate value="${gpcomm.gpcommdate}"
													pattern="yyyy-MM-dd HH:mm:ss" />
											</div>
										</c:if>
									</c:forEach></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>