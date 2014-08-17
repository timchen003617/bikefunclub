<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.blogcls.model.*"%>
<%@ page import="com.bikefunclub.blog.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%
	String servletpath = request.getServletPath();
	String path = request.getContextPath();
	BlogVO blogVO = new BlogVO();
	BlogService blogSvc = new BlogService();
	List<BlogVO> list = blogSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<div class="container body-content">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
				
					<span class="glyphicon glyphicon-search"></span>網誌列表
				</h3>
			</div>
			<div class="panel-body">
			
				<div class="table-responsive">
					<%@ include file="pages/page1.file"%>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>網誌分類</th>
								<th>發佈會員</th>
								<th>網誌標題</th>
								<th>網誌內容</th>
								<th>發佈時間</th>
								<th>詳細資料</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="blogVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<!--網誌分類-->
									<jsp:useBean id="blogclsSvc" scope="page"
										class="com.bikefunclub.blogcls.model.BlogclsService" />
									<td><c:forEach var="blogclsVO" items="${blogclsSvc.all}">
											<c:if test="${blogVO.blogclsno==blogclsVO.blogclsno}">
	                       			                            【${blogclsVO.blogclsname}】
	                       			        </c:if>
                                         </c:forEach>
                                     </td>
									<!--發佈會員-->
									<jsp:useBean id="memSvc" scope="page"
										class="com.bikefunclub.member.model.MemService" />
									<td><c:forEach var="memVO" items="${memSvc.all}">
											<c:if test="${blogVO.memno==memVO.memno}">
										        ${memVO.memname}
										    </c:if>
										</c:forEach>
									 </td>
									<!--網誌標題-->
									<td>${blogVO.bgtitle}</td>
									<!--網誌標題-->
									<td><c:out
										value="${fn:substring(blogVO.bgtext,0,25)}......" /></td>
									<!--發佈時間-->
									<td><fmt:formatDate value="${blogVO.bgtime}"
											pattern="yyyy-MM-dd HH:mm:ss" /> <!--詳細資料-->
									<td>
										<FORM METHOD="post" ACTION="<%=path%>/BlogServlet">
											<input type="submit" class="btn btn-primary" value="詳細資料"> <input
												type="hidden" name="blogno" value="${blogVO.blogno}">
											<input type="hidden" name="requestURL"
												value="<%=servletpath%>">
											<!--送出本網頁的路徑給Controller-->
											<input type="hidden" name="whichPage" value="<%=whichPage%>">
											<input type="hidden" name="action" value="getBlog_info">

										</FORM>
									</td>
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<%@ include file="pages/page3.file"%>
<%-- 		    <FORM METHOD="post" ACTION="<%=path%>/BlogServlet"> --%>
<!-- 			<input type="submit" value="發表網誌"> <input -->
<%-- 			type="hidden" name="blogno" value="${blogVO.blogno}"> <input --%>
<%-- 			type="hidden" name="requestURL" value="<%=servletpath%>"> --%>
<!--             送出本網頁的路徑給Controller -->
<!-- 			<input type="hidden" name="whichPage" -->
<%-- 			value="<%=whichPage%>"> --%>
<!--             送出當前是第幾頁給Controller -->
<!-- 			<input type="hidden" name="action" value="insert"> -->
<!-- 			</FORM> -->
            <a href="<%=path%>/front/blog/page_insert_blog.jsp"><button class="btn btn-primary btn-lg">新增網誌</button></a> 
            
			</div>
			
		</div>
		
	</div>
</div>