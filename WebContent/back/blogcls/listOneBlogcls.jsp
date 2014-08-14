<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.bikefunclub.blogcls.model.*"%>
<%
    BlogclsVO blogclsVO = (BlogclsVO) request.getAttribute("blogclsVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>相簿分類資料 - listOneBlogcls.jsp</title>
</head>
<body>



<table border='1' bordercolor='#CCCCFF' width='600'>

	<tr>
		<th>網誌分類編號</th>
		<th>網誌分類名稱</th>
	</tr>
	<tr align='center' valign='middle' var = albumVO>
		<td>${blogclsVO.blogclsno}</td>
		<td>${blogclsVO.blogclsname}</td>
	</tr>
</table>

</body>
</html>
