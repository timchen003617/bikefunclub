<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%
	String path = request.getContextPath();
	//查詢登入人的會員編號
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	Integer memno = memVO.getMemno();
	pageContext.setAttribute("memno", memno);
	//查詢未成為好友的所有會員	
	MemService memSvc = new MemService();

	List<MemVO> memlist;
	//查詢使用者輸入會員姓名搜尋出的未成為好友會員
	memlist = (List) request.getAttribute("MemVOlist");
	//查詢目前非登入會員好友的會員(預設顯示所有好友會員)
	if (memlist == null) {
		memlist = memSvc.getAllfornewfri(memno);
	}
	//移除自己
	for (int i = 0, len = memlist.size(); i < len; ++i) {
		if (memlist.get(i).getMemno() == memVO.getMemno()) {
			memlist.remove(i);
			--len;
			--i;
		}
	}
	pageContext.setAttribute("memlist", memlist);
	//tab分頁處理
	String tabNum = (request.getParameter("tabNum") == null) ? "0"
			: request.getParameter("tabNum");
%>
<script src="<%=path%>/js/tabNum.js"></script>
<!--查詢未成為好友的所有會員和自己-->
<div id="innerpanel2">
	<%@ include file="pages/page3.file"%>
	<c:forEach var="memVO" items="${memlist}" begin="<%=mempageIndex%>"
		end="<%=mempageIndex+memrowsPerPage-1%>">
		<div id="notfrimemlist" class="pull-left">
			<form method="post" action="<%=path%>/FriendListServlet" class="ins"
				name="insfri">
				<table class="table table-condensed">
					<thead>
						<tr>
							<th>大頭照</th>
							<th>姓名</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td rowspan="3"><c:choose>
									<c:when test="${memVO.memfile==null}">
										<a href="#"><img class="img-thumbnail"
											src="<%=path%>/img/photo.jpg"></a>
									</c:when>
									<c:otherwise>
										<a href="#"><img class="img-thumbnail"
											src="<%=path%>/MemreadimgServlet?memno=${memVO.memno}"></a>
									</c:otherwise>
								</c:choose></td>
							<td>${memVO.memname}</td>
						</tr>
						<tr>
							<td><strong>綽號</strong></td>
						</tr>
						<tr>
							<td>${memVO.memnickname}</td>
						</tr>
						<tr>
							<td colspan="2"><span>會員帳號:</span>${memVO.memacc}</td>
						</tr>
						<tr>
							<td colspan="2"><input type="hidden" id="actionins" name="action"
								value="insertfri"> <input type="hidden" id="tabNum"
								name="tabNum" value="<%=tabNum%>"><input type="hidden"
								id="insertyouno" name="insertyouno" value="${memVO.memno}"> <input
								type="submit" id="btninsfri" class="btn btn-primary btn-lg btn-block"
								value="關注好友"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</c:forEach>
	<div class="clearfix"></div>
	<%@ include file="pages/page4.file"%>
</div>