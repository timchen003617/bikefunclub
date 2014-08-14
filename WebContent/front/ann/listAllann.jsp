<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.Ann.model.*"%>

<%
	String servletpath = request.getServletPath();
	String path = request.getContextPath();
	AnnService annSvc = new AnnService();
	List<AnnVO> list = annSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<script type="text/javascript">
	$(document).ready(function() {
		$('.table-hover tr').click(function() {
			//$(this).attr("value");
			$("#formhidden").find("#annno").attr("value",$(this).attr("value"));
			$("#formhidden").submit();
		});				
	});
</script>	
	
<div class="container body-content">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">公告列表</h3>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
				<%@ include file="pages/page1.file"%>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>公告標題</th>
								<th>發布時間</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="annVO" items="${list}" begin="<%=pageIndex%>"
								end="<%=pageIndex+rowsPerPage-1%>">
								<tr value="${annVO.annno}">
	                       			<td>${annVO.anntitle}</td>
										
									<td><fmt:formatDate value="${annVO.anndate}"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>			
								
							</c:forEach>
						</tbody>
					</table>
					<form id="formhidden" METHOD="post" ACTION="<%=path%>/AnnServlet">
						<input type="hidden" name="action" value="getAnn_info">
						<input type="hidden" name="annno" id="annno">
					</form>
				</div>
				<%@ include file="pages/page3.file"%>
			</div>
		</div>
	</div>
</div>