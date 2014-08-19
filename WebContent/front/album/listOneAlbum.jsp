<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.bikefunclub.album.model.*"%>
<%
	AlbumVO albumVO = (AlbumVO) request.getAttribute("albumVO"); 
%>
<div class="container body-content">
	<div class="col-md-12">
	<table>
		<tr>
			<th>相簿編號</th>
			<th>會員編號</th>
			<th>相簿分類編號</th>
			<th>權限名稱</th>
			<th>相簿標題</th>
			<th>相簿描述</th>
			<th>建立時間</th>
			<td><form method="post"
					action="<%=request.getContextPath()%>/front/albcls/page_listAllAlbcls.jsp">
					<input type="submit" value="回到分類相簿"> <input type="hidden"
						name="albno" value="${albumVO.albno}">
				</form></td>
		</tr>
		<tr>
			<td>${albumVO.albno}</td>
			<td>${albumVO.memno}</td>
			<td>${albumVO.albclsno}</td>
			<td>${albumVO.authname}</td>
			<td>${albumVO.albtitle}</td>
			<td>${albumVO.albdesc}</td>
			<td>${albumVO.albtime}</td>
		</tr>
	</table>
</div>
</div>
