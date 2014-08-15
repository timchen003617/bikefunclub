<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.Ann.model.*"%>
<%@ page import="com.bikefunclub.memos.model.*"%>
<%@ page import="com.bikefunclub.member.model.*"%>
<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();
	AnnService annSvc = new AnnService();
	List<AnnVO> list = annSvc.getAll();
	pageContext.setAttribute("list", list);
	
	MemService memSvc = new MemService();
	List<MemVO> memlist = memSvc.getAll();
	pageContext.setAttribute("memlist", memlist);
%>
<script type="text/javascript">
	$(document).ready(
			function() {
				$(".ann_index").find("a").click(
						function() {
							//$(this).attr("value");
							$("#formhiddenannno").find("#annno").attr("value",
									$(this).attr("value"));
							$("#formhiddenannno").submit();
						});
				$("#memlist").find("tr").click(
						function() {
							//$(this).attr("value");
							$(this).find("#formhidden").submit();
						});
			});
</script>



<div class="col-md-4">
	<div id="colmd4-1">
		<!-- Nav tabs -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">最新公告</h3>
			</div>
			<div class="panel-body">
				<ul class="ann_index">
					<c:forEach var="annVO" items="${list}" begin="0" end="4">
						<a href='#${annVO.annno}' value='${annVO.annno}'>
							<li><c:if test="${annVO.annfile!=null}">
									<div>
										<img
											src="<%=contextpath%>/AnnreadimgServlet?annno=${annVO.annno}">
									</div>
								</c:if>
								<p>
									<c:out value="${fn:substring(annVO.anntitle,0,25)}......" />
								</p></li>
						</a>
					</c:forEach>
				</ul>
				<a href='/Bikefunclub/front/ann/page_listAllann.jsp'>
					<p class="text-right">More</p></a> 
			</div>
		</div>
	</div>

	<div id="colmd4-2">
		<!-- Nav tabs -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<span class="glyphicon glyphicon-user"></span>線上會員
				</h3>
			</div>
			<div class="panel-body">
				<div id="memlist" class="text-center">
				<c:forEach var="memVO" items="${memlist}">
						<table class="table table-hover">
						<thead>

						</thead>
							<tbody>
								<tr style="cursor:pointer">
									<td rowspan="3">
									<form id="formhidden" METHOD="post" ACTION="/Bikefunclub/front/home/page_mem_info.jsp">
						                  <input type="hidden" name="memno2" id="memno" value="${memVO.memno}">
					            	</form>
									<c:choose>
											<c:when test="${memVO.memfile==null}">
												<a href="#"><img class="img-thumbnail"
													src="<%=contextpath%>/img/photo.jpg"></a>
											</c:when>
											<c:otherwise>
												<a href="#"><img class="img-thumbnail"
													src="<%=contextpath%>/MemreadimgServlet?memno=${memVO.memno}"></a>
											</c:otherwise>
										</c:choose></td>
									<td><h3>${memVO.memname}</h3></td>	
								</tr>
							</tbody>
						</table>
				</c:forEach>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>

							<form id="formhiddenannno" METHOD="post" ACTION="<%=contextpath%>/AnnServlet">
								<input type="hidden" name="action" value="getAnn_info">
								<input type="hidden" name="annno" id="annno">
							</form>
	</div>
</div>