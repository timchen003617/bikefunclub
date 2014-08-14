<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%@ page import="com.bikefunclub.gpcls.model.*"%>
<%@ page import="com.bikefunclub.gp.model.*"%>
<%@ page import="com.bikefunclub.memgp.model.*"%>
<%
	String servletpath = request.getServletPath();
	String path = request.getContextPath();

	GpService gpSvc = new GpService();
	//登入會員VO
	MemVO loginmemVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("loginmemVO", loginmemVO);

	Integer memno = loginmemVO.getMemno();

	List<GpVO> list = gpSvc.getGpsBymemno(memno);
	pageContext.setAttribute("list", list);
	
	//取得頁籤的分頁值
	String pagetabNum = ((String) request.getParameter("tabNum") == null) ? "0"
	: (String) request.getParameter("tabNum");
%>
<div class="container body-content">
	<div class="row">
		<div class="tabbedPanels">
			<ul class="tabs">
				<li id="tab_0" onclick="setTabNum(0)"><a href="#panel1"
					data-toggle="tab">發起的揪團</a></li>
				<li id="tab_1" onclick="setTabNum(1)"><a href="#panel2"
					data-toggle="tab">已報名的揪團</a></li>
			</ul>
			<div class="tab_container">
				<!--發起的揪團 (登入會員)-->
				<div class="tab_content" id="panel1">
					<%@ include file="pages/page1.file"%>
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>揪團分類</th>
									<th>揪團名稱</th>
									<th>揪團發起時間</th>
									<th>修改</th>
									<th>刪除</th>
									<th>詳細資料</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="gpVO" items="${list}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>">
									<tr
										${(gpVO.gpno==param.gpno) ? 'style="background-color:#f5f0e9;"' : ''}>
										<jsp:useBean id="gpclsSvc" scope="page"
											class="com.bikefunclub.gpcls.model.GpclsService" />
										<td><c:forEach var="gpclsVO" items="${gpclsSvc.all}">
												<c:if test="${gpVO.gpclsno==gpclsVO.gpclsno}">【${gpclsVO.gpclsname}】
                    			</c:if>
											</c:forEach></td>
										<td>${gpVO.gptitle}</td>
										<td><fmt:formatDate value="${gpVO.gpbuilddate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>
											<form method="post" action="<%=path%>/Gp.do">
												<input class="btn btn-primary" type="submit" value="修改">
												<input type="hidden" name="gpno" value="${gpVO.gpno}">
												<input type="hidden" name="requestURL"
													value="<%=servletpath%>">
												<!--送出本網頁的路徑給Controller-->
												<input type="hidden" name="whichPage" value="<%=whichPage%>">
												<!--送出當前是第幾頁給Controller-->
												<input type="hidden" id="tabNum" name="tabNum"
													value="<%=pagetabNum%>">
												<!--送出當前是第幾頁給Controller-->
												<input type="hidden" name="action"
													value="getOne_for_updatelaunchgp">
											</form>
										</td>
										<td>
											<form method="post" action="<%=path%>/Gp.do">
												<input class="btn btn-primary" type="submit" value="刪除">
												<input type="hidden" name="gpno" value="${gpVO.gpno}">
												<input type="hidden" name="requestURL"
													value="<%=servletpath%>">
												<!--送出本網頁的路徑給Controller-->
												<input type="hidden" name="whichPage" value="<%=whichPage%>">
												<!--送出當前是第幾頁給Controller-->
												<input type="hidden" id="tabNum" name="tabNum"
													value="<%=pagetabNum%>">
												<!--送出當前是第幾頁給Controller-->
												<input type="hidden" name="action" value="delete_launchgp">
											</form>
										</td>
										<td>
											<form method="post" action="<%=path%>/Gp.do">
												<input class="btn btn-primary" type="submit" value="查看">
												<input type="hidden" name="gpno" value="${gpVO.gpno}">
												<input type="hidden" name="requestURL"
													value="<%=servletpath%>"> <input type="hidden"
													name="gpno" value="${gpVO.gpno}">
												<!--送出本網頁的路徑給Controller-->
												<input type="hidden" name="whichPage" value="<%=whichPage%>">
												<!--送出當前是第幾頁給Controller-->
												<input type="hidden" id="tabNum" name="tabNum"
													value="<%=pagetabNum%>"> <input type="hidden"
													name="action" value="launchgp_info">
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<%@ include file="pages/page3.file"%>
				</div>
				<%
					List<GpVO> attendlist = gpSvc.getGpsBymemnoFromMemgp(memno);
					pageContext.setAttribute("attendlist", attendlist);
				%>
				<jsp:useBean id="now" class="java.util.Date" />
				<!--已報名的揪團 -->
				<div class="tab_content" id="panel2">
					<%@ include file="pages/page4.file"%>
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>揪團分類</th>
									<th>揪團名稱</th>
									<th>發起時間</th>
									<th>發起人</th>
									<th>揪團狀態</th>
									<th>退出揪團</th>
									<th>詳細資料</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="gpVO2" items="${attendlist}"
									begin="<%=attendpageIndex%>"
									end="<%=attendpageIndex+attendrowsPerPage-1%>">
									<tr
										${(gpVO2.gpno==param.gpno) ? 'style="background-color:#f5f0e9;"' : ''}>
										<jsp:useBean id="gpclsSvc2" scope="page"
											class="com.bikefunclub.gpcls.model.GpclsService" />
										<td><c:forEach var="gpclsVO2" items="${gpclsSvc2.all}">
												<c:if test="${gpVO2.gpclsno==gpclsVO2.gpclsno}">【${gpclsVO2.gpclsname}】
                    								</c:if>
											</c:forEach></td>
										<td>${gpVO2.gptitle}</td>
										<td><fmt:formatDate value="${gpVO2.gpbuilddate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<jsp:useBean id="memSvc" scope="page"
											class="com.bikefunclub.member.model.MemService" />
										<td><c:forEach var="memVO" items="${memSvc.all}">
												<c:if test="${memVO.memno==gpVO2.memno}">${memVO.memname}</c:if>
											</c:forEach></td>
										<td><c:choose>
												<c:when
													test="${now>=gpVO2.joinstartdate&&now<=gpVO2.joinenddate}">報名進行中</c:when>
												<c:when
													test="${now>=gpVO2.gpstartdate&&now<=gpVO2.gpenddate}">揪團活動進行中</c:when>
												<c:when test="${now>gpVO2.gpenddate}">揪團已結束</c:when>
											</c:choose></td>
										<td><c:choose>
												<c:when
													test="${now<gpVO2.gpenddate&&loginmemVO.memno!=gpVO2.memno}">
													<!-- 揪團已結束不可以退出揪團 or 自己發起的揪團無法退出-->
													<form method="post" action="<%=path%>/Gp.do">
														<input class="btn btn-primary" type="submit" value="退出">
														<input type="hidden" name="gpno" value="${gpVO2.gpno}">
														<input type="hidden" name="requestURL"
															value="<%=servletpath%>">
														<!--送出本網頁的路徑給Controller-->
														<input type="hidden" name="whichPage2"
															value="<%=attendwhichPage%>">
														<!--送出當前是第幾頁給Controller-->
														<input type="hidden" id="tabNum" name="tabNum"
															value="<%=pagetabNum%>"> <input type="hidden"
															name="loginmemno" value="${loginmemVO.memno}"> <input
															type="hidden" name="action" value="leavegp">
													</form>
												</c:when>
												<c:otherwise></c:otherwise>
											</c:choose></td>
										<td>
											<form method="post" action="<%=path%>/Gp.do">
												<input class="btn btn-primary" type="submit" value="查看">
												<input type="hidden" name="gpno" value="${gpVO2.gpno}">
												<input type="hidden" name="requestURL"
													value="<%=servletpath%>">
												<!--送出本網頁的路徑給Controller-->
												<input type="hidden" name="whichPage2"
													value="<%=attendwhichPage%>">
												<!--送出當前是第幾頁給Controller-->
												<input type="hidden" id="tabNum" name="tabNum"
													value="<%=pagetabNum%>"> <input type="hidden"
													name="gpno" value="${gpVO2.gpno}"> <input
													type="hidden" name="action" value="launchgp_info">
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<%@ include file="pages/page5.file"%>
				</div>
			</div>
		</div>
	</div>
</div>
