<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.bikefunclub.album.model.*"%>
<%
    AlbumVO albumVO = (AlbumVO) request.getAttribute("albumVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>相簿資料 - listOneAlbum.jsp</title>
</head>
<body>



<table border='1' bordercolor='#CCCCFF' width='600'>

	<tr>
		<th>相簿編號</th>
		<th>會員編號</th>
		<th>相簿分類編號</th>
		<th>權限名稱</th>
		<th>相簿標題</th>
		<th>相簿描述</th>
		<th>建立時間</th>
		<td><FORM METHOD="post" action="<%=request.getContextPath()%>/front/albcls/page_listAllAlbcls.jsp">
			     <input type="submit" value="回到分類相簿">
			     <input type="hidden" name="albno" value="${albumVO.albno}">
			</FORM></td>
	</tr>
	<tr align='center' valign='middle' var = albumVO>
		<td>${albumVO.albno}</td>
		<td>${albumVO.memno}</td>
		<td>${albumVO.albclsno}</td>
		<td>${albumVO.authname}</td>
		<td>${albumVO.albtitle}</td>
		<td>${albumVO.albdesc}</td>
		<td>${albumVO.albtime}</td>
	</tr>
</table>

</body>
</html>
