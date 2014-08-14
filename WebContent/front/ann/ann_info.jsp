<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.Ann.model.*"%>

<%
	String path = request.getContextPath();
	AnnVO annVO = (AnnVO) request.getAttribute("annVO");
	pageContext.setAttribute("annVO", annVO);
%>

<div class="container body-content">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">公告</h3>
			</div>
			<div class="table-responsive">
				<table class="table table-hover">
					<thead>
						<tr>
							<td class="col-md-2"><strong>公告標題</strong></td>
							<td class="col-md-10">${annVO.anntitle}</td>
						</tr>
					</thead>
					<tbody>
						<c:if test="${annVO.annfile!=null}">
							<tr>
								<th>公告圖片</th>
								<td><img id="ann"
									src="<%=path%>/AnnreadimgServlet?annno=${annVO.annno}" style="width:auto;height:auto;max-width:100%;max-height:100%"></td>
							</tr>
						</c:if>
						<tr>
							<th>公告內容</th>
							<td style="word-wrap: break-word; word-break: break-all;">${annVO.anncontent}</td>
						</tr>

						<tr>
							<th>公告日期</th>
							<td style="word-wrap: break-word; word-break: break-all;"><fmt:formatDate
									value="${annVO.anndate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>

					</tbody>
				</table>

			</div>
		</div>
	</div>
</div>
