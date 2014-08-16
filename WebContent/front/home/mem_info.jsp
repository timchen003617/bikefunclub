<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.friendlist.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%@ page import="com.bikefunclub.gp.model.*"%>
<%@ page import="com.bikefunclub.rot.model.*"%>
<%
	String path = request.getContextPath();
    Integer memno = Integer.parseInt(request.getParameter("memno"));
	MemService memSvc2 = new MemService();
	MemVO memVO =memSvc2.getOnemem(memno);
	pageContext.setAttribute("memVO", memVO);

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
%>
<jsp:useBean id="memSvc" scope="page"
	class="com.bikefunclub.member.model.MemService" />
<div class="container body-content">
	<div class="row">
		<div id="myhome">
			<div id="myhome-inner">
				<div id="mypic">
					<c:if test="${memVO.memfile==null}">
						<img class="img-thumbnail" src="<%=path%>/img/photo.jpg">
					</c:if>
					<c:if test="${memVO.memfile!=null}">
						<img class="img-thumbnail"
							src="<%=path%>/MemreadimgServlet?memno=${memVO.memno}">
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
								<td>姓名: ${memVO.memname}</td>
								<td>綽號: ${memVO.memnickname}</td>
								<td>性別: ${memVO.memsex=="M"?"男":"女"}</td>
								<td>生日: ${memVO.membirth}</td>
							</tr>
							<tr>
								<td>地址: ${memVO.memaddr}</td>
								<td>手機: ${memVO.memtelm}</td>
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
												<span>${memVO.memname} </span>
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

			</div>
		</div>
	</div>
</div>