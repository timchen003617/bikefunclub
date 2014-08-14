<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.bikefunclub.photo.model.*"%>
<%
	PhotoVO photoVO = (PhotoVO) request.getAttribute("photoVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>相片資料 - listOnePhoto.jsp</title>
</head>
<body>


	<table border='1' bordercolor='#CCCCFF' width='600'>

		<tr>
			<th>相片編號</th>
			<th>會員編號</th>
			<th>相片經緯度</th>
			<th>相片關聯</th>
			<th>相片檔名</th>
			<th>相片副檔名</th>
			<th>相片上傳時間</th>
			<th>相片檔案</th>
		</tr>
		<tr align='center' valign='middle' var=photoVO>
			<td>${photoVO.photono}</td>
			<td>${photoVO.memno}</td>
			<td>${photoVO.phcoo}</td>
			<td>${photoVO.phass}</td>
			<td>${photoVO.phfilename}</td>
			<td>${photoVO.phextname}</td>
			<td>${photoVO.phup}</td>
			<td><img
				src="<%=request.getContextPath()%>/PhotoPreViewServlet?photono=${photoVO.photono}"></td>
		</tr>
	</table>

</body>
</html>
