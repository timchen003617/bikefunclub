<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bikefunclub.ad.model.*"%>
<%@ page import="com.bikefunclub.Ann.model.*" %>
<%
	String path = request.getContextPath();
%>


<div id="backmain" class="col-md-10">
	<h1 class="page-header">公告新增</h1>
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">公告新增</h3>
		</div>
		<div class="panel-body">
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<p class="red">請修正以下錯誤:</p>
				<ul class="red">
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<form method="post" action="<%=path%>/AnnServlet" name="form1"
				enctype="multipart/form-data">
				<div class="table-responsive">
					<table class="table table-bordered">
						<tr>
							<th>公告標題</th>
							<td><input type="text" name="anntitle" size="110"
								value="${annVO.anntitle}" required></td>
						</tr>
						<tr>
							<th>公告內文</th>
							<td><textarea rows="10" cols="109" name="anncontent">${annVO.anncontent}</textarea>								
						</tr>
						<tr>
							<th>公告圖片</th>
 							<td><!--預覽圖片顯示區域 -->
								<div id="preview">
									<img id="imghead" class="ann" src="#">
								</div><br>
								<!--圖片上傳區域 -->					
								<input type="file" id="InputFile" name="annfile"
								value="${annVO.annfilename}.${annVO.annextname}" onchange="previewImage(this,'imghead')"></td>

						</tr>
					</table>
				</div>
				<input type="hidden" name="action" value="insert"> 
				<input class="btn btn-warning btn-lg" type="submit" value="確定新增"> 
			</form>
		</div>
	</div>
</div>