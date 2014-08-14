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
	GpVO gpVO = (GpVO) request.getAttribute("gpVO");
	Integer gpno = gpVO.getGpno();

	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);

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
				<h3 class="panel-title">發起揪團詳細資料</h3>
			</div>
			<div class="panel-body">
				<div class="text-right">
					<input class="btn btn-warning" type="button" value="上一頁"
						onClick="history.back();return true;" name="button">
				</div>
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<td class="col-md-2"><strong>揪團編號</strong></td>
								<td class="col-md-10">${gpVO.gpno}</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>發起會員</th>
								<td>${memVO.memname}</td>
							</tr>
							<tr>
								<jsp:useBean id="gpclsSvc" scope="page"
									class="com.bikefunclub.gpcls.model.GpclsService" />
								<th>揪團分類</th>
								<td><c:forEach var="gpclsVO" items="${gpclsSvc.all}">
										<c:if test="${gpVO.gpclsno==gpclsVO.gpclsno}">${gpclsVO.gpclsname}
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
								<th>注意事項</th>
								<td style="word-wrap: break-word; word-break: break-all;">${gpVO.gpnote}</td>
							</tr>

							<tr>
								<th>發起時間</th>
								<td><fmt:formatDate value="${gpVO.gpbuilddate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>

							<tr>
								<th>報名開始日期</th>
								<td><fmt:formatDate value="${gpVO.joinstartdate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>

							<tr>
								<th>報名結束日期</th>
								<td><fmt:formatDate value="${gpVO.joinenddate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>

							<tr>
								<th>活動開始日期</th>
								<td><fmt:formatDate value="${gpVO.gpstartdate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>

							<tr>
								<th>活動結束日期</th>
								<td><fmt:formatDate value="${gpVO.gpenddate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>

							<tr>
								<th>人數限制</th>
								<td>${gpVO.gpmaxnum}</td>
							</tr>
							<tr>
								<th>報名人數</th>
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
									</c:forEach> <input type="hidden" id="rotno" value="${gpVO.rotno}">
									<input style="margin-left: 10px;" type="button" id="rotmap"
									class="btn btn-primary" value="地圖預覽" /> <c:forEach var="rotVO"
										items="${rotSvc.all}">
										<c:if test="${rotVO.rotno==gpVO.rotno}">
											<div id="previewrotmap" title="${rotVO.rotname}"
												style="display: none"></div>
										</c:if>
									</c:forEach></td>
							</tr>
							<tr>
								<th>參加會員</th>
								<td><c:set var="i" value="0"></c:set> <c:forEach
										var="memgpmemname" items="${memgpVO}">
										<c:if test="${i>0}">
											<span>、</span>
										</c:if>								
									${memgpmemname.memname}
									<c:set var="i" value="${i+1}"></c:set>
									</c:forEach></td>
							</tr>
						</tbody>
					</table>

				</div>
			</div>
		</div>
	</div>
</div>