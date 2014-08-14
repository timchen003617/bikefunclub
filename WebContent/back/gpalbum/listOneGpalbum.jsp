<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.bikefunclub.albcls.model.*"%>
<%
	AlbclsVO albclsVO = (AlbclsVO) request.getAttribute("albclsVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>相簿分類資料 - listOneAlbcls.jsp</title>
</head>
<body>


	<table border='1' bordercolor='#CCCCFF' width='600'>
		<tr>
			<th>相簿分類編號</th>
			<th>相簿分類名稱</th>
		</tr>
		<tr align='center' valign='middle'>
			<td><%=albclsVO.getAlbclsno()%></td>
			<td><%=albclsVO.getAlbclsname()%></td>
		</tr>
	</table>

</body>
</html>
