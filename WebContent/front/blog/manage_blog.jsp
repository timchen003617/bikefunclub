<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%@ page import="com.bikefunclub.blogcls.model.*"%>
<%@ page import="com.bikefunclub.blog.model.*"%>

<%
	String servletpath = request.getServletPath();
	String path = request.getContextPath();
    BlogVO blogVO = new BlogVO();
	BlogService blogSvc = new BlogService();
	//登入會員VO
	MemVO loginmemVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("loginmemVO", loginmemVO);

	Integer memno = loginmemVO.getMemno();

	List<BlogVO> list = blogSvc.getBlogs_frommemno(memno);
	pageContext.setAttribute("list", list);
	String tabNum = (request.getParameter("tabNum") == null) ? "0"
			: request.getParameter("tabNum");
%>
<div class="container body-content">
	<div class="row">
	      <div class="text-right">
					<input class="btn btn-warning" type="button" value="上一頁"
						onClick="history.back();return true;" name="button">
				</div>
		<div class="tabbedPanels">
			<ul class="tabs">
<!-- 				<li><a href="#panel1" data-toggle="tab" onclick="setTabNum(0)">會員所屬網誌</a></li> -->
				<li><a href="#panel2" data-toggle="tab" onclick="setTabNum(1)">會員所屬網誌</a></li>
			</ul>
			<div class="tab_container">
				<!--發起的網誌(登入會員)-->
				<div class="tab_content" id="panel1">
					<%@ include file="pages/page1.file"%>
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>網誌分類</th>
									<th>網誌名稱</th>
			                        <th>網誌修改時間</th>
									<th>修改</th>
									<th>刪除</th>
									<th>詳細資料</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="blogVO" items="${list}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>">
									<tr
										${(blogVO.blogno==param.blogno) ? 'style="background-color:#f5f0e9;"' : ''}>
										<jsp:useBean id="blogclsSvc" scope="page"
											class="com.bikefunclub.blogcls.model.BlogclsService" />
										<td><c:forEach var="blogclsVO" items="${blogclsSvc.all}">
												<c:if test="${blogVO.blogclsno==blogclsVO.blogclsno}">【${blogclsVO.blogclsname}】
                    			</c:if>
											</c:forEach></td>
										<td>${blogVO.bgtitle}</td>
										<td><fmt:formatDate value="${blogVO.bgtime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>
											<form method="post" action="<%=path%>/BlogServlet">
												<input class="btn btn-primary" type="submit" value="修改">
												<input type="hidden" name="blogno" value="${blogVO.blogno}">
												<input type="hidden" name="requestURL"
													value="<%=servletpath%>">
												<!--送出本網頁的路徑給Controller-->
												<input type="hidden" name="whichPage" value="<%=whichPage%>">
												<!--送出當前是第幾頁給Controller-->
												<input type="hidden" name="action"
													value="getOne_For_Update">
											</form>
										</td>
										<td>
											<form method="post" action="<%=path%>/BlogServlet">
												<input class="btn btn-primary" type="submit" value="刪除">
												<input type="hidden" name="blogno" value="${blogVO.blogno}">
												<input type="hidden" name="requestURL"
													value="<%=servletpath%>">
												<!--送出本網頁的路徑給Controller-->
												<input type="hidden" name="whichPage" value="<%=whichPage%>">
												<!--送出當前是第幾頁給Controller-->
												<input type="hidden" name="action" value="delete_launchblog">
											</form>
										</td>
										<td>
											<form method="post" action="<%=path%>/BlogServlet">
												<input class="btn btn-primary" type="submit" value="查看">
												<input type="hidden" name="blogno" value="${blogVO.blogno}">
												<input type="hidden" name="requestURL"
													value="<%=servletpath%>"> <input type="hidden"
													name="blogno" value="${blogVO.blogno}">
												<!--送出本網頁的路徑給Controller-->
												<input type="hidden" name="whichPage" value="<%=whichPage%>">
												<!--送出當前是第幾頁給Controller-->
												<input type="hidden"
													name="action" value="getBlog_info">
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<%@ include file="pages/page3.file"%>
				</div>
			</div>
		</div>
	</div>
</div>
