<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.Ann.model.*"%>
<%
	String servletpath = request.getServletPath();
	String contextpath = request.getContextPath();
	AnnService annSvc = new AnnService();
	List<AnnVO> list = annSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<script type="text/javascript">
	$(document).ready(function() {
		$(".ann_index").find("a").click(function() {
			//$(this).attr("value");
			$("#formhiddenannno").find("#annno").attr("value",$(this).attr("value"));
			$("#formhiddenannno").submit();
		});				
	});
</script>	



<div class="col-md-4">
	<div id="colmd4-1">
		<!-- Nav tabs -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					<span class="glyphicon glyphicon-user"></span>最新公告
				</h3>
			</div>
			<div class="panel-body">
				<ul class="ann_index">
					<c:forEach var="annVO" items="${list}" begin="0" end="4">
						<a href='#${annVO.annno}' value='${annVO.annno}'>
						<li><c:if test="${annVO.annfile!=null}">
								<div>
									<img src="<%=contextpath%>/AnnreadimgServlet?annno=${annVO.annno}">
								</div>
							</c:if>
							<p><c:out value="${fn:substring(annVO.anntitle,0,25)}......"/><span> 
<%-- 							<fmt:formatDate --%>
<%-- 										value="${annVO.anndate}" pattern="yyyy-MM-dd" /> --%>
 </span>
							</p></li></a>
					</c:forEach>

					
					<a href='/Bikefunclub/front/ann/page_listAllann.jsp'><p class="text-right">More</p></a>

				</ul>
			</div>
		</div>
	</div>
	<div id="colmd4-2">
		<div class="tabbedPanels">
			<ul class="tabs">
				<li><a href="#panel1">最新揪團</a></li>
				<li><a href="#panel2">推薦路線</a></li>
				<li><a href="#panel3">會員分享路線</a></li>
			</ul>
			<div class="tab_container">
				<div class="tab_content" id="panel1">
					<ul>
						<li><a href="#">news1</a></li>
						<li><a href="#">news2</a></li>
						<li><a href="#">news3</a></li>
					</ul>
				</div>
				<div class="tab_content" id="panel2">
					<p>Panel 2 content</p>
				</div>
				<div class="tab_content" id="panel3">
					<p>Panel 3 content</p>
				</div>
					<form id="formhiddenannno" METHOD="post" ACTION="<%=contextpath%>/AnnServlet">
						<input type="hidden" name="action" value="getAnn_info">
						<input type="hidden" name="annno" id="annno">
					</form>
			</div>
		</div>
	</div>
</div>