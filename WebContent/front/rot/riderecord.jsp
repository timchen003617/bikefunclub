<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.riderecord.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>

<%
	String path = request.getContextPath();
	String servletpath = request.getServletPath();
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	Integer memno = memVO.getMemno();
	pageContext.setAttribute("memno",memno);
    
    RideRecordService riderecordSvc = new RideRecordService();    
    List<RideRecordVO> list_riderecord = riderecordSvc.getrotrcds_bymemno(memno);  
    pageContext.setAttribute("list_riderecord",list_riderecord);
%>

<div class="container body-content">
	<div class="col-md-13">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">騎車紀錄</h3>
			</div>
			<div class='span12'>
				<table class="table table-hover">
					<thead>
						<tr class="text-center">
							<td>您一共紀錄了</td>
							<td><span style="font-weight: bold">${fn:length(list_riderecord)}</span>
							</td>
							<td>次</td>
						</tr>
					</thead>
					<tbody class="text-center">
						<tr>
							<td class="col-md-5"><c:set var="sumdistence" value="0"></c:set>
								<c:forEach var="riderecordVO" items="${list_riderecord}">
									<fmt:formatNumber var="recorddistence" type="number"
										groupingUsed="false" value="${riderecordVO.recorddistence}" />
									<c:set var="sumdistence"
										value="${sumdistence + recorddistence}" />
								</c:forEach> <fmt:formatNumber var="sumdistence" type="number"
									groupingUsed="false" value="${sumdistence/1000}" />總距離數</td>
							<td class="col-md-1"><span style="font-weight: bold">${sumdistence}</span></td>
							<td class="col-md-5">km</td>
						</tr>
						<tr>
							<td><c:forEach var="riderecordVO" items="${list_riderecord}">
									<fmt:formatNumber var="ridetime"
										value="${riderecordVO.ridetime}" type="number"
										groupingUsed="false" />
									<c:set var="sumtime" value="${sumtime + ridetime}" />
								</c:forEach> <fmt:formatNumber var="sumtime" value="${sumtime/60/60}"
									groupingUsed="false" type="number" />總時數</td>
							<td><span style="font-weight: bold">${sumtime}</span></td>
							<td>hr</td>
						</tr>
						<tr>
							<td>平均時速</td>
							<td><span style="font-weight: bold"><fmt:formatNumber
										value="${sumdistence/sumtime}" groupingUsed="false"
										type="number" /></span></td>
							<td>km/hr</td>
						</tr>
						<tr>
							<td>總卡路里</td>
							<td><span style="font-weight: bold"><fmt:formatNumber
										value="${sumtime*184.0}" groupingUsed="false" type="number" /></span>
							</td>
							<td>卡路里</td>
						</tr>
					</tbody>
				</table>
			</div>
			<hr style="height: 3px; border: none; border-top: 2px ridge #6b3" />
			<c:forEach var="riderecordVO" items="${list_riderecord}">
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
									<tr>
									<td><fmt:formatDate value="${riderecordVO.stamp}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td></td>
									<td></td>
																		<td>
										<FORM METHOD="post" ACTION="<%=path%>/Rot.do">
											<input class="btn btn-success" type="submit" value="查看路線資料">
											<input type="hidden" name="rotno"
												value="${riderecordVO.rotno}"> <input type="hidden"
												name="requestURL" value="<%=servletpath%>">
											<!--送出本網頁的路徑給Controller-->
											<!--送出當前是第幾頁給Controller-->
											<input type="hidden" name="action" value="getRotrecord_info">
										</FORM>
									</td>
								</tr>
							</thead>
							<tbody class="text-center">
								<tr>
									<td><fmt:formatNumber var="recorddistence" type="number"
											groupingUsed="false"
											value="${riderecordVO.recorddistence/1000}" /> 距離數 <span
										style="font-weight: bold">${recorddistence}</span> km</td>
									<td><fmt:formatNumber var="ridetime"
											value="${riderecordVO.ridetime/60/60}" type="number"
											groupingUsed="false" /> 時數 <span style="font-weight: bold">${ridetime}</span>
										hr</td>
									<td>平均時速 <span style="font-weight: bold"><fmt:formatNumber
												value="${recorddistence/ridetime}" groupingUsed="false"
												type="number" /></span> km/hr
									</td>
									<td></td>				
								</tr>
								<tr><td>
									消耗卡路里
									<span style="font-weight: bold"><fmt:formatNumber
											value="${ridetime*184.0}" groupingUsed="false" type="number" /></span>
									卡路里
									</td>
									<td></td>
									<td></td>
									<td></td>
									<jsp:useBean id="rotclsSvc" scope="page"
										class="com.bikefunclub.rotcls.model.RotclsService" />
									<td><c:forEach var="rotclsVO" items="${rotclsSvc.all}">
											<c:if test="${rotVO.rotclsno==rotclsVO.rotclsno}">
	                       				    【<font color=orange>${rotclsVO.rotclsname}</font>】
                    			</c:if>
										</c:forEach></td>

								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
