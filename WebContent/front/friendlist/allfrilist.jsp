<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%@ page import="com.bikefunclub.friendlist.model.*"%>
<%
	String path = request.getContextPath();
	FriendListService friSvc = new FriendListService();
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	//查詢使用者輸入好友姓名搜尋出的好友
	List<FriendListVO> frilist;

	frilist = (List) request.getAttribute("FriendListVOlist");
	//查詢目前登入會員的所有好友(預設顯示所有好友)
	if (frilist == null) {
		frilist = friSvc.getAllFriendList(memVO.getMemno());
	}
	pageContext.setAttribute("frilist", frilist);
	//tab分頁處理
	String tabNum = (request.getParameter("tabNum") == null) ? "0"
			: request.getParameter("tabNum");
%>
<script src="<%=path%>/js/tabNum.js"></script>
<script src="<%=path%>/js/dialog.js"></script>
<jsp:useBean id="memSvc" scope="page"
	class="com.bikefunclub.member.model.MemService" />
<div id="delfri-confirm" title="刪除好友確認" style="display:none;">
	<p>是否刪除好友?</p>		
</div>	
<div id="innerpanel1">
	<%@ include file="pages/page1.file"%>
	<c:forEach var="friVO" items="${frilist}" begin="<%=fripageIndex%>"
		end="<%=fripageIndex+frirowsPerPage-1%>">
		<div id="frilist" class="pull-left">
			<form class="del" method="post" action="<%=path%>/FriendListServlet"
				id="del${friVO.youno}" name="delfriend">
				<table class="table table-condensed">
					<thead>
						<tr>
							<th>大頭照</th>
							<th>姓名</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td rowspan="3"><c:forEach var="memVO" items="${memSvc.all}">
									<c:if test="${friVO.youno==memVO.memno}">
										<c:choose>
											<c:when test="${memVO.memfile==null}">
												<a href="#"><img class="img-thumbnail"
													src="<%=path%>/img/photo.jpg"></a>
											</c:when>
											<c:otherwise>
												<a href="#"><img class="img-thumbnail"
													src="<%=path%>/MemreadimgServlet?memno=${memVO.memno}"></a>
											</c:otherwise>
										</c:choose>
									</c:if>
								</c:forEach></td>
							<td><c:forEach var="memVO" items="${memSvc.all}">
									<c:if test="${friVO.youno==memVO.memno}">${memVO.memname}
								</c:if>
								</c:forEach></td>
						</tr>
						<tr>
							<td><strong>綽號</strong></td>
						</tr>
						<tr>
							<td><c:forEach var="memVO" items="${memSvc.all}">
									<c:if test="${friVO.youno==memVO.memno}">
								${memVO.memnickname}
								</c:if>
								</c:forEach></td>
						</tr>
						<tr>
							<td colspan="2"><c:forEach var="memVO" items="${memSvc.all}">
									<c:if test="${friVO.youno==memVO.memno}">
										<span>會員帳號:</span>${memVO.memacc}
												</c:if>
								</c:forEach></td>
						</tr>
						<tr>
							<td colspan="2"><input type="hidden" id="actiondel" name="action"
								value="deletefri"><input type="hidden" id="tabNum"
								name="tabNum" value="<%=tabNum%>"> <input type="hidden"
								id="deleteyouno" name="deleteyouno" value="${friVO.youno}"> <input
								id="delfri" type="button" name="${friVO.youno}"
								class="btn btn-primary btn-lg btn-block" value="取消關注"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</c:forEach>
	<div class="clearfix"></div>
	<%@ include file="pages/page2.file"%>
</div>