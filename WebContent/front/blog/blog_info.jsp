<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.blog.model.*"%>
<%@ page import="com.bikefunclub.blogcls.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%@ page import="com.bikefunclub.blogcom.model.*"%>
<%
	String path = request.getContextPath();
    BlogVO blogVO = (BlogVO) request.getAttribute("blogVO");
	
   
	String servletpath = request.getServletPath();

	BlogService blogSvc = new BlogService();
	List<BlogVO> list = blogSvc.getAll();
	pageContext.setAttribute("list", list);

	MemService memsvc = new MemService();
	List<MemVO> memlist = memsvc.getAll();
	pageContext.setAttribute("memlist", memlist);
	//網誌分類
	BlogclsService blogclssvc = new BlogclsService();
	List<BlogclsVO> list3 = blogclssvc.getAll();
	pageContext.setAttribute("list3", list3);
	//網誌留言
	BlogcomService blogcomSvc = new BlogcomService();
	List<BlogcomVO> blogcomlist = blogcomSvc.getAll();
	pageContext.setAttribute("blogcomlist", blogcomlist);
	
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	if (memVO == null) {
		RequestDispatcher successView = request
		.getRequestDispatcher("/front/mem/login.jsp");
		successView.include(request, response);
		return;
	}
%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#blogcomlist").find("#memlist").click(function() {
			//$(this).attr("value");
			$(this).find("#formhidden").submit();
		});

		$("#sumit_btn").click(function() {

			$("#bgcomtext").focus();

		});
	});
</script>





<div class="container body-content">
	<div class="row">
		<div id="backmain" class="col-md-13">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">網誌資料</h3>
				</div>
				<div class="panel-body">
					<div class="text-right">
						<a class="btn btn-primary"
						href="<%=path%>/front/blog/page_listAllblog.jsp">回瀏覽網誌</a>
					</div>
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								
							</thead>
							<tbody>
								<tr>
									<!--會員編號 -->
									<th>發起會員姓名</th>
									<td><c:forEach var="MemVO" items="${memlist}">
											<c:if test="${blogVO.memno==MemVO.memno}">
										    ${MemVO.memname}
									  </c:if>
										</c:forEach></td>
								</tr>

								<tr>

									<th>網誌分類</th>
									<td><c:forEach var="blogclsVO" items="${list3}">
											<c:if test="${blogVO.blogclsno==blogclsVO.blogclsno}">
									 ${blogclsVO.blogclsname}
							   </c:if>
										</c:forEach></td>
								</tr>

								<tr>
									<th>網誌標題</th>
									<td>${blogVO.bgtitle}</td>
								</tr>

								<tr>
									<th>網誌內容</th>
									<td>${blogVO.bgtext} </td>
								</tr>
								<tr>
									<th>網誌更改時間</th>
									<td><fmt:formatDate value="${blogVO.bgtime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
							</tbody>
						</table>
						<!--此區塊為我要留言的按紐 -->
						<div>

							<%-- 							<FORM METHOD="post" ACTION="<%=path%>/BlogcomServlet"> --%>
							<input class="btn btn-success" id="sumit_btn" type="button"
								value="我要留言"> <input type="hidden" name="blogcom"
								value="${riderecordVO.rotno}"> <input type="hidden"
								name="requestURL" value="<%=servletpath%>">
							<!--送出本網頁的路徑給Controller-->
							<!--送出當前是第幾頁給Controller-->
							<input type="hidden" name="action" value="getRotrecord_info">
							<!-- 							</FORM> -->


						</div>
					</div>
				</div>
               
				<!--此區塊為會員顯示區 -->
				<div id="blogcomlist" class="text-center">
				    <!--我是分隔線-->
					<hr style="height: 3px; border: none; border-top: 2px ridge #6b3" />
					
					
					<!--判斷網誌留言是否屬這個網誌(P.140行的判斷句) -->	
					<c:forEach var="blogcomVO" items="${blogcomlist}">
                        <c:if test="${blogVO.blogno == blogcomVO.blogno}">
						   <table class="table table-hover">
							
							<thead></thead>
							
							<tbody>
								<tr>
									<td id="memlist" class="col-md-1">
										<form id="formhidden" method="post"
											action="<%=path%>/front/home/page_mem_info.jsp">
											<input type="hidden" name="memno" id="memno"
												value="${blogcomVO.memno}">
										</form> <c:forEach var="memVO" items="${memlist}">
											<c:if test="${blogcomVO.memno==memVO.memno}">
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
									</td>
									<td class="col-md-1"><p>${memVO.memname}</p></td>
									</c:if>
									</c:forEach>

									<td class="col-md-1"><fmt:formatDate value="${blogcomVO.bgcomtime}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>

									<td class="col-md-7"
										style="word-wrap: break-word; word-break: break-all;">${blogcomVO.bgcomtext}</td>
									<td>
                                    <!-- 判斷此留言是否是由同一個會員 -->
									
										<FORM method="post" action="<%=path%>/BlogcomServlet">
										<c:if test="${blogcomVO.memno == memVO.memno}">
										
									      
											<input class="btn btn-primary" type="submit" value="刪除留言 ">
											</c:if>
											<input type="hidden" name="blogno"
												value="${blogcomVO.blogno}">
											<input type="hidden" name="bgcomno"
												value="${blogcomVO.bgcomno}"> <input type="hidden"
												name="requestURL" value="<%=servletpath%>">
											<!--送出本網頁的路徑給Controller-->
											<input type="hidden" name="whichPage"
												value="<%=request.getAttribute("whichPage")%>">
											<!--送出當前是第幾頁給Controller-->
											<input type="hidden" name="action" value="delete_fromFront">
										</FORM>





									</td>
								</tr>
								
							</tbody>
							
						</table>
                         </c:if>
					</c:forEach>
					
					<div>
						<!--此區塊為會員留言發送區-->
						<FORM METHOD="post" ACTION="<%=path%>/BlogcomServlet">
							<label>我要留言</label>
							<textarea name="bgcomtext" id="bgcomtext" rows="4" cols="100"
								placeholder="請輸入網誌留言內容" required>${blogcomVO.bgcomtext}</textarea>
							<input class="btn btn-success" type="submit" value="送出留言">
							<input type="hidden" name="requestURL" value="<%=servletpath%>">
							<input type="hidden" name="requestURL"
								value="<%=request.getAttribute("requestURL")%>">
							<!--送出本網頁的路徑給Controller-->
							<!--送出當前是第幾頁給Controller-->
							<input type="hidden" name="whichPage"
								value="<%=request.getAttribute("whichPage")%>"> <input
								type="hidden" name="memno" value="${memVO.memno}"> <input
								type="hidden" name="blogno" value="${blogVO.blogno}"> <input
								type="hidden" name="action" value="insert">
						</FORM>

					</div>

					<br />

				</div>
			</div>
		</div>
	</div>
</div>