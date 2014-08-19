<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bikefunclub.album.model.*"%>


<div id="backmain" class="col-md-10">
	<h1 class="page-header">新增相簿</h1>  
	<div class="panel panel-warning">
		<div class="panel-heading">
			<h3 class="panel-title">新增相簿</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/AlbumServlet">
	<div class="table table-bordered">
	<table class="table table-bordered">
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memno" size = "45"
		     value ="${param.memno}"/></td><td>${errorMsgs.memno}</td>

	</tr>
	
	<tr>
		<td>相簿分類編號:</td>
		<td><input type="TEXT" name="albclsno" size = "45"
		     value ="${param.albclsno}"/></td><td>${errorMsgs.albclsno}</td>
	</tr>
	
	<!-- 權限名稱:PERSONAL,SHAREFS,PUBLIC -->
	<tr>
		<td>權限名稱:</td>
		<td><select size="1" name="authname">
		    <option value="PERSONAL">個人</option>
		    <option value="SHAREFS">分享好友</option>
		    <option value="PUBLIC">公開</option>
		    </select> 
			
	</tr>
	<tr>
		<td>相簿標題:</td>
		<td><input type="TEXT" name="albtitle" size="45" 
			 value ="${param.albtitle}"/></td><td>${errorMsgs.albtitle}</td>
	</tr>
	<tr>
		<td>相簿描述:</td>
		<td><input type="TEXT" name="albdesc" size="45" 
			 value="${param.albdesc }"/></td>${errorMsgs.albdesc}</td>
	</tr>
	</div>
	</table>


<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
