<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.friendlist.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%@ page import="com.bikefunclub.gp.model.*"%>
<%@ page import="com.bikefunclub.rot.model.*"%>
<%@ page import="com.bikefunclub.riderecord.model.*"%>
<%
	String path = request.getContextPath();
	MemVO loginmemVO = (MemVO) session.getAttribute("memVO");//登入者的會員編號
	pageContext.setAttribute("loginmemVO", loginmemVO);
	Integer memno = loginmemVO.getMemno();
	//會員好友清單
	FriendListService friSvc = new FriendListService();
	List<FriendListVO> friendList = friSvc.getAllFriendList(memno);
	pageContext.setAttribute("friendList", friendList);
	//參加過的揪團
	GpService gpSvc = new GpService();
	List<GpVO> attendlist = gpSvc.getGpsBymemnoFromMemgp(memno);
	pageContext.setAttribute("attendlist", attendlist);

	//會員收藏路線
	RotService rotSvc = new RotService();
	List<RotVO> rotlist = rotSvc.getrotsBymemnoFromMemrot(memno);
	pageContext.setAttribute("rotlist", rotlist);

	//騎乘過的路線
	RideRecordService recordSvc = new RideRecordService();
	List<RideRecordVO> recordlist = recordSvc.getrotrcds_bymemno(memno);
	pageContext.setAttribute("recordlist", recordlist);
%>
<jsp:useBean id="memSvc" scope="page"
	class="com.bikefunclub.member.model.MemService" />
<div class="container body-content">
	<div class="row">
		<div id="myhome">
			<div id="myhome-inner">
				<div id="mypic">
					<c:if test="${loginmemVO.memfile==null}">
						<img class="img-thumbnail" src="<%=path%>/img/photo.jpg">
					</c:if>
					<c:if test="${loginmemVO.memfile!=null}">
						<img class="img-thumbnail"
							src="<%=path%>/MemreadimgServlet?memno=${loginmemVO.memno}">
					</c:if>
				</div>
				<div id="fieldperson">
					<table class="table">
						<thead>
							<tr>
								<th><h3>
										<strong>基本資料</strong>
									</h3></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>姓名: ${loginmemVO.memname}</td>
								<td>綽號: ${loginmemVO.memnickname}</td>
								<td>性別: ${loginmemVO.memsex=="M"?"男":"女"}</td>
								<td>生日: ${loginmemVO.membirth}</td>
							</tr>
							<tr>
								<td>地址: ${loginmemVO.memaddr}</td>
								<td>手機: ${loginmemVO.memtelm}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="field">
					<table class="table">
						<thead>
							<tr>
								<th><h3>
										<strong>好友名單</strong>
									</h3></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><c:set var="i" value="0"></c:set> <c:forEach
										var="friVO" items="${friendList}">
										<c:if test="${i>0}">
											<span>、</span>
										</c:if>
										<c:forEach var="memVO" items="${memSvc.all}">
											<c:if test="${friVO.youno==memVO.memno}">
												<a
													href="<%=path%>/front/home/page_mem_info.jsp?memno=${memVO.memno}"
													target="_blank">${memVO.memname}</a>
											</c:if>
										</c:forEach>
										<c:set var="i" value="${i+1}"></c:set>
									</c:forEach></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="field">
					<table class="table">
						<thead>
							<tr>
								<th><h3>
										<strong>參加過的揪團</strong>
									</h3></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="attendVO" items="${attendlist}">
								<tr>
									<td>
										<form action="<%=path%>/Gp.do" method="post">
											<a href="javascript:;" onclick="parentNode.submit();">【活動日期:<fmt:formatDate
													value="${attendVO.gpstartdate}" pattern="yyyy-MM-dd" />】【揪團名稱:${attendVO.gptitle}】
											</a><input type="hidden" name="action" value="launchgp_info"><input
												type="hidden" name="gpno" value="${attendVO.gpno}">
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="field">
					<table class="table">
						<thead>
							<tr>
								<th><h3>
										<strong>收藏路線</strong>
									</h3></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="rotVO" items="${rotlist}">
								<c:set var="i" value="0"></c:set>
								<c:if test="${i>0}">
									<span>、</span>
								</c:if>
								<tr>
									<td>
										<form action="<%=path%>/Rot.do" method="post">
											<a href="javascript:;" onclick="parentNode.submit();">【路線名稱:${rotVO.rotname}】</a><input
												type="hidden" name="action" value="getRot_info"><input
												type="hidden" name="rotno" value="${rotVO.rotno}">
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="field">
					<table class="table">
						<thead>
							<tr>
								<th><h3>
										<strong>騎乘過的路線</strong>
									</h3></th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="riderecordVO" items="${recordlist}">

								<tr>
									<td>
										<form action="<%=path%>/Rot.do" method="post">
											<a href="javascript:;" onclick="parentNode.submit();">【騎乘日期】<fmt:formatDate
													value="${riderecordVO.stamp}" pattern="yyyy-MM-dd" /></a>
											<input type="hidden" name="action" value="getRotrecord_info">
											<input type="hidden" name="rotno"
												value="${riderecordVO.rotno}">
										</form>
									</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>