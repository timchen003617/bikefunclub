<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%@ page import="com.bikefunclub.friendlist.model.*"%>
<%
	String path = request.getContextPath();
	MemVO memVO = (MemVO) session.getAttribute("memVO");//登入者的會員編號
%>
<c:if test="${memVO.memno!=null}">
	<%
		FriendListService friSvc = new FriendListService();
			// 	登入會員的好友清單
			List<FriendListVO> list = friSvc.getAllFriendList(memVO
					.getMemno());
			pageContext.setAttribute("list", list);
	%>
	<jsp:useBean id="memSvc" scope="page"
		class="com.bikefunclub.member.model.MemService" />
	<div id="slider_scroll">
		<div id="fb_tab">
			<h3>+</h3>
			<span>好</span><span>友</span><span>列</span><span>表</span>
		</div>
		<div id="slider_content">
			<div class="hideslider">隱藏邊攔</div>
			<input id="openchatbox" class="btn btn-primary btn-lg" type="button" value="開啟公開聊天"/>
				<c:forEach var="friVO" items="${list}">
					<div class="sliderlist">
						<ul>
							<c:forEach var="memVO2" items="${memSvc.all}">
								<c:if test="${friVO.youno==memVO2.memno}">								
									<c:if test="${memVO2.memfile==null}">
											<li><img class="img-thumbnail"
												src="<%=path%>/img/photo.jpg">${memVO2.memname}</li>
									</c:if>
									<c:if test="${memVO2.memfile!=null}">
											<li><img class="img-thumbnail"
												src="<%=path%>/MemreadimgServlet?memno=${memVO2.memno}">${memVO2.memname}</li>
									</c:if>								
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
		</div>
	</div>
 	<div id="chatbox">
		<div id="closechatbox">
			<span class="glyphicon glyphicon-remove"></span>
		</div>
		<div id="chatbox-inner">
			<div id="chattext">
				<ul id="chatlog">
				</ul>
			</div>
		</div>
		<div id="chatbox-input">
			<hr>
			<input type="hidden" id="sendermemno" value="${memVO.memno}"> <input
				id="text" onkeypress="dwr.util.onReturn(event, sendMessage)" />
		</div>
 	</div>
</c:if>